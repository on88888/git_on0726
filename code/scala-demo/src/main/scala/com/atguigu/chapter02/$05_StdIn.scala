package com.atguigu.chapter02

import scala.io.{Source, StdIn}

object $05_StdIn {

  /**
    * 从控制台读取数据: StdIn.readLine()/readInt/...
    * 从文件读取数据: Source.fromFile(path).getLines
    */
  def main(args: Array[String]): Unit = {

    val line = StdIn.readLine("请输入一行句子:")
    println(line)

    val lines = Source.fromFile("E:\\sources\\scala-2.12.10\\scala-2.12.10\\versions.properties").getLines
    while(lines.hasNext){
      println(lines.next())
    }
  }
}
