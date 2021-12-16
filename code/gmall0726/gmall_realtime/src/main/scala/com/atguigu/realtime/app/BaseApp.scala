package com.atguigu.realtime.app

import org.apache.spark.streaming.StreamingContext

/**
 * Created by Smexy on 2021/11/30
 *
 *  如果一个类有抽象属性，这个类必须得是抽象类
 */
abstract class  BaseApp {

  //抽象属性，留给子类实现
  val appName:String

  val batchDuration:Int

  // 需要子类来重写父类中的属性
  var context :StreamingContext = null

  // 需要让子类来调用，将子类自己的运算逻辑 code传入
  def runApp(code: => Unit) :Unit={

    try {

      code

      context.start()

      context.awaitTermination()
    } catch {
      case e:Exception =>{

        e.printStackTrace()

        throw new RuntimeException("运行出错!")

      }
    }


  }

}
