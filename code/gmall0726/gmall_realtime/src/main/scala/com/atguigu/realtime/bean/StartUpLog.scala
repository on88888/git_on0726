package com.atguigu.realtime.bean

/**
 * Created by Smexy on 2021/11/30
 *
 *    主键作为rowkey, 主键是什么？
 *
 *          参考粒度：  每一天，每个设备，启动时间最早的一条log
 *
 *                  日期，设备id
 */
case class StartUpLog(
                       //common
                       var ar:String,
                       var ba:String,
                       var  ch:String,
                       var is_new:Int,
                       var md:String,
                       var mid:String,
                       var os:String,
                       var  uid:Long,
                       var   vc:String,
                       // start
                       var entry:String,
                       var loading_time:Int,
                       var open_ad_id:Int,
                       var open_ad_ms:Int,
                       var open_ad_skip_ms:Int,
                       //kafka里面没有，为了统计最终结果，额外设置的字段，需要从ts转换得到
                       var logDate:String,
                       var logHour:String,
                       var ts:Long){


  def mergeStartInfo(startInfo:StartLogInfo):Unit={

    if (startInfo != null){

      this.entry = startInfo.entry
      this.loading_time = startInfo.loading_time
      this.open_ad_id = startInfo.open_ad_id
      this.open_ad_ms = startInfo.open_ad_ms
      this.open_ad_skip_ms = startInfo.open_ad_skip_ms

    }
  }
}

case class StartLogInfo(
                         entry:String,
                         loading_time:Int,
                         open_ad_id:Int,
                         open_ad_ms:Int,
                         open_ad_skip_ms:Int
                       )
