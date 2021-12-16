package com.atguigu.realtime.bean

// 没有展平，不需要展平，不需要写入hbase
case class ActionsLog(
                       actions: List[Action],
                       ts:Long,
                       common:CommonInfo
                     )