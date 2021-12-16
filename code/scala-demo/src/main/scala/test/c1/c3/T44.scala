package test.c1.c3

import java.util

/**
 * 元素：element
 *
 * @Author 0726
 * @ClassName T44
 * @createTime 2021年10月22日 19:09
 */
object T44 {
  def main(args: Array[String]): Unit = {

    val strings: Array[String] = Array[String]("zhangsan man shenzhen", "wangwu woman beijing", "zhaoliu woman shenzhen")

    val func = (x:String) => x.split(" ")(2)

    println(map(strings, func))

  }

  def map(array: Array[String] ,func:String=>String) ={

    val hashMap = new util.HashMap[String,util.List[String]]()
    for(ele<-array){

      var key = func(ele)

      if(hashMap.containsKey(key)){

        val list: util.List[String] = hashMap.get(key)
        list.add(ele)

      }else{

        val list = new util.ArrayList[String]()
        list.add(ele)
        hashMap.put(key,list)

      }

    }
    hashMap

  }

}
