package com.atguigu.realtime.bean

// actions:[{Action}]
case class Action(action_id:String,
                  item:String,
                  item_type:String,
                  ts:Long
                 )