package com.atguigu.realtime.utils

/**
 * Created by Smexy on 2021/11/30
 */
import java.io.InputStreamReader
import java.util.Properties

// 加载类路径的一个properties文件，只需要调用load方法，自动将properties文件读取，封装一个Properties返回
object PropertiesUtil {

  def load(propertieName:String): Properties ={
    val prop=new Properties()
    prop.load(new InputStreamReader(Thread.currentThread().getContextClassLoader.getResourceAsStream(propertieName) , "UTF-8"))
    prop
  }

  def main(args: Array[String]): Unit = {

    val properties: Properties = load("config.properties")

    println(properties)

  }

}
