package test.c1

/**
 * @Author 0726
 * @ClassName Test4
 * @createTime 2021年10月20日 19:00
 */

object Test4 {
  /**
   * 4、根据指定规则对数组的数据进行分组
   * 数据: Array[String]("zhangsan man shenzhen","wangwu woman beijing","zhaoliu woman shenzhen")
   * 规则: 按照地址分组
   * 结果: Map( shenzhen->List("zhangsan man shenzhen","zhaoliu woman shenzhen" ) , beijing->List("wangwu woman beijing") )
   *
   * 思路:1.创建函数以空格分割，取下标为2的地址，作为Map集合的key
   *      2.创建Map集合，此时集合没有元素，需要添加对应的key以及value
   *      3.将获取的key以及对应的value放到map中
   *      4.如果数据中的key在map中不存在，则重复3
   *      5.如果数据中的key在map中存在，则以map.get(key).add(value),将获取的vakue值添加到该键值对中
   *      注：value是数据中的每一个元素,需要创建Map集合和List集合(接收value)，否则没有map.get(key).add(value)这个方法
   *
   * @param args
   */
  def main(args: Array[String]): Unit = {

//    val arr = Array("zhangsan man shenzhen","wangwu woman beijing","zhaoliu woman shenzhen")
    val arr = Array("zhangsan man shenzhen","wangwu woman beijin","zhaoliu woman shenzhen","lisi woman beijin")
//    val map = new java.util.HashMap[String,java.util.List[String]]()
//    val func = (x:String) => {
//      x.split(" ").toList(2)
//    }
      val func = (x:String) => x.split(" ").apply(2)
//    println(arr(1).split(" ").toList(2))
    println(city(arr,func))

  }

  def city(datas:Array[String],func:(String)=>String) ={

    val map = new java.util.HashMap[String,java.util.List[String]]()

    for(ele <- datas){
      val key = func(ele)
      if(map.containsKey(key)) {
        //map.get(key).add(ele)  //将获取的vakue值添加到该键值对中
        val list = map.get(key)
        list.add(ele)

//        println("key: "+key)
//        println("get_key: "+map.get(key))  //获取键值key所对应的value值  map(key,value)
//        println("add:"+map.get(key).add(ele)) //将获取的vakue值添加到该键值对中

      }else{    //如果key不存在则创建键值对并放到map中
        val list = new java.util.ArrayList[String]()
//        println("get_key: "+map.get(key))
//        println("key:"+ key)
//        println("ele:"+ele)
        list.add(ele)
        map.put(key, list)
        //println("1:"+map)
      }
      //println("2"+map)
    }
    map
  }
}
