package com.atguigu.realtime.test

import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.time.{Instant, LocalDateTime, ZoneId}
import java.util.Date

/**
 * Created by Smexy on 2021/11/30
 */
object TimeParseDemo {

  def main(args: Array[String]): Unit = {

    val ts = 1638147218000L

    val date = new Date(ts)

    val dateFormat1 = new SimpleDateFormat("yyyy-MM-dd")
    val dateFormat2 = new SimpleDateFormat("HH")

    println(dateFormat1.format(date))
    println(dateFormat2.format(date))

    println("--------------------------java8 提供的新的api 线程安全----------------")


    // Date ---------> LocalDate | LocalDateTime
    //  SimpleDateFormat -------------> DateTimeFormatter
    //  对象.方法        ---------------> 静态方法

    val dateTime: LocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(ts), ZoneId.of("Asia/Shanghai"))

    val formatter1: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formatter2: DateTimeFormatter = DateTimeFormatter.ofPattern("HH")

    println(dateTime.format(formatter1))
    println(dateTime.format(formatter2))
  }

}
