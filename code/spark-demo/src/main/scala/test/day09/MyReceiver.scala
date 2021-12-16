package test.day09

import java.io.{BufferedReader, InputStream, InputStreamReader}
import java.net.Socket

import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver

/**
 * @Author 0726
 * @ClassName MyReceiver
 * @createTime 2021年11月07日 11:39
 */
class MyReceiver(val hostName:String,port:Int) extends Receiver[String](StorageLevel.MEMORY_AND_DISK) {


  override def onStart(): Unit = {
    new Thread(){
      override def run() = {
        receiver
      }
    }.start()
  }

  def receiver: Unit ={
    val socket = new Socket(hostName,port)

//    val is: InputStream = socket.getInputStream
    val br = new BufferedReader(new InputStreamReader(socket.getInputStream))

    var line = br.readLine()

    while (line!=null){
      store(line)
      line = br.readLine()
    }

    br.close()
    socket.close()
  }

  override def onStop(): Unit = {

  }
}
