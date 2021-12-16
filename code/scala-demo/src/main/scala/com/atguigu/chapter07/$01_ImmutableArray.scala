package com.atguigu.chapter07

object $01_ImmutableArray {

  /**
    * 不可变数组的创建
    *       1、通过new方式: new Array[元素类型](数组的长度)
    *       2、通过apply方法: Array[元素类型](初始元素,...)<常用>
    *
    * 所有集合通用的添加[+:、:+、+=、+=:、++、++:、++=、++=:]/删除[-、-=、--、--=]方法区别:
    *     带+与带-的区别:
    *         带+是添加元素
    *         带-是删除元素
    *     一个+/-与两个+/-的区别:
    *         一个+/-是添加/删除单个元素
    *         两个+/-是添加/删除一个集合所有元素
    *     带冒号与冒号在前、冒号在后的区别:
    *         冒号在前是将元素添加在集合最末尾
    *         冒号在后是将元素添加在集合最前面
    *         带冒号是将元素添加在集合最末尾
    *     带=与不带=的区别:
    *         带=是修改集合本身
    *         不带=是生成新集合,原集合没有改变
    *      update与updated的区别:
    *         update是修改集合本身元素
    *         updated是生成新集合,原集合元素没有改变
    *
    */
  def main(args: Array[String]): Unit = {

    //1、通过new方式: new Array[元素类型](数组的长度)
    val arr = new Array[Int](5)
    println(arr.toList)

    //2、通过apply方法: Array[元素类型](初始元素,...)
    val arr2 = Array[Int](10,3,2,6,5)

    println(arr2.toList)

    //添加元素
    //添加单个元素
    val arr3 = arr2.+:(20)
    println(arr2.toList)
    println(arr3.toList)
    println(arr2)
    println(arr3)

    val arr4 = arr2.:+(30)
    println(arr4.toList)

    //添加一个集合所有元素
    val arr5 = arr2.++(Array(100,300,400))
    println(arr5.toList)

    val arr6 = arr2.++:(Array(100,300,400))
    println(arr6.toList)

    //删除

    //获取指定角标元素
    println(arr6(0))

    //修改指定角标元素
    arr6(0)=1000
    println(arr6.toList)

    for(element<- arr6){
      println(element)
    }

    //不可变数组转可变数组
    val arr11 = arr6.toBuffer
    println(arr11)
  }
}
