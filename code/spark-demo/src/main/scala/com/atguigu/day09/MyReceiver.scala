package com.atguigu.day09

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket

import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver

class MyReceiver(val hostName:String,port:Int) extends Receiver[String](StorageLevel.MEMORY_AND_DISK){
  /**
    * receiver启动的时候调用
    */
  override def onStart(): Unit = {

    new Thread(){
      override def run(): Unit = {
        receive
      }
    }.start()
  }

  def receive: Unit = {
    val socket = new Socket(hostName,port)

    val br = new BufferedReader( new InputStreamReader( socket.getInputStream ) )

    var line = br.readLine()

    while( line != null) {
      //存放数据,积累一个批次之后统一处理
      store(line)

      line = br.readLine()
    }

    br.close()

    socket.close()
  }

  /**
    * receiver停止的时候调用
    */
  override def onStop(): Unit = {

  }
}
