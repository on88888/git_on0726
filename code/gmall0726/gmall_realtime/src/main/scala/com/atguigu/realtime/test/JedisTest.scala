package com.atguigu.realtime.test

import java.util

import com.alibaba.fastjson.JSON
import com.atguigu.realtime.utils.RedisUtil
import com.google.gson.Gson
import redis.clients.jedis.Jedis

/**
 * Created by Smexy on 2021/12/6
 *
 * Error:(33, 18) ambiguous reference to overloaded definition,
 * both method toJSONString in class JSON of type (x$1: Any, x$2: com.alibaba.fastjson.serializer.SerializerFeature*)String
 * and  method toJSONString in class JSON of type (x$1: Any)String
 * match argument types (com.atguigu.realtime.test.Cat) and expected result type Any
 * println(JSON.toJSONString(cat))
 */
object JedisTest {

  def main(args: Array[String]): Unit = {

  val jedis: Jedis = RedisUtil.getJedisClient()

  // []
  val set: util.Set[String] = jedis.smembers("aaaaa")

  println(set)

  // null
  val str: String = jedis.get("bbbbbb")

  println(str)

  jedis.close()

    // 创建一个Cat()
    val cat: Cat = Cat("喵喵")

    // fastjson 在scala中将一个对象转为 string会有歧义
    //println(JSON.toJSONString(cat))

    println(new Gson().toJson(cat))

}

}

case class Cat(var name:String)
