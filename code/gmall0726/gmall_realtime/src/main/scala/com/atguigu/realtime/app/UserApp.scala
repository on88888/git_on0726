package com.atguigu.realtime.app

import com.alibaba.fastjson.{JSON, JSONObject}
import com.atguigu.gmall.constants.TopicName
import com.atguigu.realtime.utils.{MyKafkaUtil, RedisUtil}
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{CanCommitOffsets, HasOffsetRanges, OffsetRange}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import redis.clients.jedis.Jedis

/**
 * Created by Smexy on 2021/12/6
 *
 *    精确一次： at least once + 幂等输出
 */
object UserApp extends BaseApp {
  override val appName: String = "UserApp"
  override val batchDuration: Int = 10

  val groupId="0726gmall"

  def main(args: Array[String]): Unit = {

    context = new StreamingContext("local[*]",appName,Seconds(batchDuration))

    runApp{

      val ds: InputDStream[ConsumerRecord[String, String]] = MyKafkaUtil.getKafkaStream(Array(TopicName.GMALL_USER_INFO), context, groupId)

      ds.foreachRDD(rdd => {

        if (!rdd.isEmpty()){

          // 获取偏移量
          val ranges: Array[OffsetRange] = rdd.asInstanceOf[HasOffsetRanges].offsetRanges

          val rdd1: RDD[String] = rdd.map(record => record.value())

          println("即将写入:"+rdd1.count())

          rdd1.foreachPartition(partition => {

            val jedis: Jedis = RedisUtil.getJedisClient()

            partition.foreach(userInfoJsonStr => {

              val jSONObject: JSONObject = JSON.parseObject(userInfoJsonStr)

              jedis.set("userinfo:" + jSONObject.getString("id") , userInfoJsonStr);

            })

            jedis.close()


          })
          // ⑥提交偏移量
          ds.asInstanceOf[CanCommitOffsets].commitAsync(ranges)

        }

      })

    }


  }
}
