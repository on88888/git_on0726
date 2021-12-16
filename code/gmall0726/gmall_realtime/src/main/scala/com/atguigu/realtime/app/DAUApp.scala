package com.atguigu.realtime.app

import java.time.format.DateTimeFormatter
import java.time.{Instant, LocalDateTime, ZoneId}

import com.alibaba.fastjson.{JSON, JSONObject}
import com.atguigu.gmall.constants.TopicName
import com.atguigu.realtime.bean.{StartLogInfo, StartUpLog}
import com.atguigu.realtime.utils.{MyKafkaUtil, RedisUtil}
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.{CanCommitOffsets, HasOffsetRanges, OffsetRange}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import redis.clients.jedis.Jedis

//导入phoenix提供的集成的工具类
  // rdd =>  ProductRDDFunctions 调用saveToPhoenix
import org.apache.phoenix.spark._

/**
 * Created by Smexy on 2021/11/30
 *
 *  kryo : 设置让kryo来序列化ConsumerRecord
 *
 *
 *      at least once + 输出幂等 实现 精确一次
 *
 *          Offset 维护再kafka的主题中!
 */
object DAUApp extends BaseApp {

  override val appName: String = "DAUApp"
  override val batchDuration: Int = 10

  val groupId ="gmall0726"

  def parseRecordToStartUpLog(rdd: RDD[ConsumerRecord[String, String]]): RDD[StartUpLog] = {

    rdd.map(record => {

      val jSONObject: JSONObject = JSON.parseObject(record.value())

      val commonStr: String = jSONObject.getString("common")

      val startUpLog: StartUpLog = JSON.parseObject(commonStr, classOf[StartUpLog])

      // 将start部分封装为 StartLogInfo，合并到 StartUpLog中
      val startLogInfo: StartLogInfo = JSON.parseObject(jSONObject.getString("start"), classOf[StartLogInfo])

      startUpLog.mergeStartInfo(startLogInfo)

      // 放入ts
      startUpLog.ts = jSONObject.getLong("ts")

      val dateTime: LocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(startUpLog.ts), ZoneId.of("Asia/Shanghai"))

      val formatter1: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
      val formatter2: DateTimeFormatter = DateTimeFormatter.ofPattern("HH")

      startUpLog.logDate = dateTime.format(formatter1)
      startUpLog.logHour = dateTime.format(formatter2)

      startUpLog

    })


  }

  /*
      当前批次的数据跨天：
            2021--11-30,mid1,log1

            2021--12-01,mid1,log2
      按照mid_id
   */
  def removeDuplicateLogInCurrentBatch(rdd: RDD[StartUpLog]):  RDD[StartUpLog] = {

  /*  val rdd1: RDD[(String, StartUpLog)] = rdd.map(log => (log.mid, log))
    val rdd2: RDD[(String, Iterable[StartUpLog])] = rdd1.groupByKey()
    val rdd3: RDD[StartUpLog] = rdd2.flatMap {
      case (mid_id, logs) => {

        logs.toList.sortBy(_.ts).take(1)

      }
    }*/
    val rdd1: RDD[StartUpLog] = rdd.map(log => ((log.logDate,log.mid), log))
      .groupByKey()
      .flatMap {
        case ((logDate,mid_id), logs) => {

          logs.toList.sortBy(_.ts).take(1)

        }
      }
    rdd1


  }

  def removeDuplicateLogFromHistoryBatch(rdd: RDD[StartUpLog]): RDD[StartUpLog] = {

    val rdd1: RDD[StartUpLog] = rdd.mapPartitions(partition => {

      //获取连接
      val jedis: Jedis = RedisUtil.getJedisClient()

      //使用连接  判断分区中每一个元素(log.mid)，是否之前已经记录过
      val filteredLogs: Iterator[StartUpLog] = partition.filter(log => !jedis.sismember("DAU:" + log.logDate, log.mid))

      jedis.close()

      filteredLogs
    })
    rdd1


  }

  def main(args: Array[String]): Unit = {

    context = new StreamingContext("local[*]",appName,Seconds(batchDuration))

    runApp{

      // ①消费数据获取DS
      val ds: InputDStream[ConsumerRecord[String, String]] = MyKafkaUtil.getKafkaStream(Array(TopicName.STARTUP_LOG), context, groupId)

      // ConsumerRecord 没有实现序列化!

      // 需要将ConsumerRecord从kafkabroker机器，传输到Spark运行的机器，中间涉及到序列化

      ds.foreachRDD(rdd => {

        if (!rdd.isEmpty()){

          // 获取偏移量
          val ranges: Array[OffsetRange] = rdd.asInstanceOf[HasOffsetRanges].offsetRanges

          // 将初始DS中的 ConsumerRecord 转换为 样例类
          val rdd1: RDD[StartUpLog] = parseRecordToStartUpLog(rdd)

          // ② 将数据进行同批次去重，只取同一个批次中，每个设备，启动时间最早的日志
          val rdd2: RDD[StartUpLog] = removeDuplicateLogInCurrentBatch(rdd1)

          // ③跨批次去重，今天已经记录过启动日志的设备，在当前批次中的启动日志时无效的，过滤掉
          val rdd3: RDD[StartUpLog] = removeDuplicateLogFromHistoryBatch(rdd2)

          rdd3.cache()

          println("即将写入:"+rdd3.count())

          // ④ 将剩下的日志，写入hbase
          rdd3.saveToPhoenix(
            "GMALL2021_STARTUPLOG",
            //  cols: Seq[String]:  按照RDD中样例类每个属性的顺序，编写和属性对应的列的顺序
            Seq("AR", "BA", "CH", "IS_NEW", "MD", "MID", "OS", "UID", "VC", "ENTRY", "LOADING_TIME","OPEN_AD_ID","OPEN_AD_MS","OPEN_AD_SKIP_MS","LOGDATE","LOGHOUR","TS"),
            //   conf: Configuration = new Configuration : 只读hadoop的配置文件
            //  HBaseConfiguration.create()； 先读hadoop，再读Hbase的配置文件
            HBaseConfiguration.create(),
            Some("hadoop102:2181")
          )

          // ⑤ 将写入hbase的日志的mid_id，记录在redis中
          rdd3.foreachPartition(partition => {

            val jedis: Jedis = RedisUtil.getJedisClient()

            partition.foreach(log =>  jedis.sadd("DAU:"+log.logDate , log.mid))

            jedis.close()

          })

          // ⑥提交偏移量
          ds.asInstanceOf[CanCommitOffsets].commitAsync(ranges)
        }

      })

    }

  }

}
