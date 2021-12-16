package com.atguigu.chapter02

object $04_String {

  /**
    * java获取字符串的方式:
    *   1、通过""方式: String name = "zhangsan"
    *   2、通过new的方式: String name = new String("...")
    *   3、通过+拼接的方式: String hello = "lisi"+"hello"
    *   4、通过一些方法: toString,SubString....,format
    *
    * scala获取字符串的方式:
    *   1、通过""方式: val name = "zhangsan"
    *   2、通过new的方式: val name = new String("...")
    *   3、拼接
    *       1、通过+拼接的方式: val hello = "lisi"+"hello"
    *       2、插值表达式: s"${变量名/表达式} ..."
    *   4、通过一些方法: toString,SubString....,format
    *   5、通过三引号的方式: """字符串""" [一般用于写sql语句]
    */
  def main(args: Array[String]): Unit = {

    //1、通过""方式: String name = "zhangsan"
    val name = "zhangsan"

    //2、通过new的方式: val name = new String("...")
    val name2 = new String("lisi")

    //3、通过+拼接的方式: String hello = "lisi"+"hello"
    val hello = "lisi"+"_"+"hello"
    println(name)
    println(name2)
    println(hello)

    //4、插值表达式: s"${变量名/表达式/值} ..."
    val hello2 = s"${name}_hello_${1+1}_10"
    println(hello2)

    val host = "hadoop102"
    val port = 9870
    val url = "http://%s:%d/aa/bb/cc"
    println(url.format(host, port))

    val sql =
      """
        | select
        |	a.id,a.name,a.age,b....
        |		from (
        |			select * from person where id>=15
        |			) a
        |		left join (
        |			select
        |				t1.id,t1.name,t2.age
        |					from student t1
        |					inner join class t2
        |					on t1.classid=t2.id)
        |		b on a.id=b.id
      """.stripMargin

    println(sql)

  }
}
