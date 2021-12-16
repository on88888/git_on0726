package test.c1




/**
 * @Author 0726
 * @ClassName Test5
 * @createTime 2021年10月20日 20:06
 */
object Test5 {
  /**
   * 5、根据指定规则获取数组最大元素
   * 数据: Array[String]("zhangsan 25 4000","wangwu 18 6000","zhaoliu 45 1200")
   * 规则: 获取年龄最大的元素
   * 结果: "zhaoliu 45 1200"
   *
   * 思路:1.创建函数以空格分割，依题意取下标为1的元素，需 .toInt转化为整数类型
   *      2.假设第一个元素是最大值datas(0)，分割出年龄对应的元素放入变量tmpFild中
   *      3.将其与所有的数据进行比较，大于则不变，小于则与其替换，以替换后的较大值继续进行比较，直到所有数据都比较完后可以确定其为最大值
   *
   * @param args
   */
  def main(args: Array[String]): Unit = {

    val arr = Array("zhangsan 25 4000","wangwu 18 6000","zhaoliu 45 1200")
//    val func = (x:String) => x.split(" ").toList(1).toInt
    val func = (x:String) => {
      x.split(" ").apply(1).toInt
    }
//    println(arr(2).split(" ").toList(1))
/*
    val list = new java.util.ArrayList[String]()
    list.add(max(arr,func))
    println(list)
*/
    println(max(arr,func))
  }

  def max(datas:Array[String],func:String=>Int)={
    var tmp = datas(0)
    var tmpFild = func(tmp)

    for(ele<-datas){
        var filed = func(ele)
        if(filed>tmpFild){
          tmpFild = filed
          tmp = ele
        }
    }
    tmp
  }
}
