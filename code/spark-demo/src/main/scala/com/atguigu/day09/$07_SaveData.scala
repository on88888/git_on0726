package com.atguigu.day09

import java.sql.{Connection, DriverManager, PreparedStatement}

object $07_SaveData {

  def main(args: Array[String]): Unit = {

    import org.apache.spark.SparkConf
    import org.apache.spark.streaming.{Seconds, StreamingContext}
    val conf = new SparkConf().setMaster("local[4]").setAppName("test")
    val ssc = new StreamingContext(conf,Seconds(5))
    ssc.sparkContext.setLogLevel("error")

    val ds = ssc.socketTextStream("hadoop102",9999)

    ds.flatMap(_.split(" "))
      .map((_,1))
      .reduceByKeyAndWindow(_+_,windowDuration = Seconds(15),Seconds(5))
      .foreachRDD(rdd=> {
        rdd.foreachPartition((it)=> {
          var connection:Connection = null
          var statement:PreparedStatement = null
          try{
            connection = DriverManager.getConnection(".....","","")
            statement = connection.prepareStatement("insert into person values(?,?,?)")
            var i = 0
            it.foreach((x)=>{
              //sql参数赋值
              //statement.setInt(1,x._1)
              //statement.setString(2,x._2)
              //statement.setDouble(3,x._3)
              //加入一个批次中
              statement.addBatch()

              if(i%1000==0){
                statement.executeBatch()
                statement.clearBatch()
              }
              i = i+1
            })

            //最后不满1000条的批次处理
            statement.executeBatch()

          }catch {
            case e:Exception => e.printStackTrace()
          }finally {
            if(statement!=null)
              statement.close()
            if(connection!=null)
              connection.close()
          }
        } )

      })

    ssc.start()
    ssc.awaitTermination()
  }
}
