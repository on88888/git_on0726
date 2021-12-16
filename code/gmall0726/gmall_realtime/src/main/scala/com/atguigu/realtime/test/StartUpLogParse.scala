package com.atguigu.realtime.test

import java.time.{Instant, LocalDateTime, ZoneId}
import java.time.format.DateTimeFormatter

import com.alibaba.fastjson.{JSON, JSONObject}
import com.atguigu.realtime.bean.{StartLogInfo, StartUpLog}

/**
 * Created by Smexy on 2021/11/30
 */
object StartUpLogParse {

  def main(args: Array[String]): Unit = {

    val str =
      """
        |
        |{
        |  "common": {
        |    "ar": "440000",
        |    "ba": "Xiaomi",
        |    "ch": "web",
        |    "is_new": "1",
        |    "md": "Xiaomi 10 Pro ",
        |    "mid": "mid_115",
        |    "os": "Android 9.0",
        |    "uid": "636",
        |    "vc": "v2.1.134"
        |  },
        |  "start": {
        |    "entry": "icon",
        |    "loading_time": 12363,
        |    "open_ad_id": 14,
        |    "open_ad_ms": 7241,
        |    "open_ad_skip_ms": 0
        |  },
        |  "ts": 1638147218000
        |}
        |
        |
        |""".stripMargin


    // 将common部分取出，赋值生成一个StartUpLog对象

    val jSONObject: JSONObject = JSON.parseObject(str)

    val commonStr: String = jSONObject.getString("common")

    val startUpLog: StartUpLog = JSON.parseObject(commonStr, classOf[StartUpLog])

    println(startUpLog)

    // 将start部分封装为 StartLogInfo，合并到 StartUpLog中
    val startLogInfo: StartLogInfo = JSON.parseObject(jSONObject.getString("start"), classOf[StartLogInfo])

    startUpLog.mergeStartInfo(startLogInfo)

    println(startUpLog)


    // 放入ts
    startUpLog.ts = jSONObject.getLong("ts")

    val dateTime: LocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(startUpLog.ts), ZoneId.of("Asia/Shanghai"))

    val formatter1: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formatter2: DateTimeFormatter = DateTimeFormatter.ofPattern("HH")

    startUpLog.logDate = dateTime.format(formatter1)
    startUpLog.logHour = dateTime.format(formatter2)

    println(startUpLog)



  }

}
