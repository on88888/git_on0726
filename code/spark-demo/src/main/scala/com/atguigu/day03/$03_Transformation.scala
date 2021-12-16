package com.atguigu.day03

import java.sql.{Connection, DriverManager, PreparedStatement}

import org.junit.Test

/**
  * spark rdd算子分为两大类:
  *   转换算子[Transformation]: 不会触发任务的计算,只是封装数据的计算过程,转换算子的结果类型是RDD
  *   行动算子[Action]: 会触发任务的计算,行动算子的结果不是RDD类型
  */
class $03_Transformation {

  import org.apache.spark.{SparkConf, SparkContext}
  val sc = new SparkContext( new SparkConf().setMaster("local[4]").setAppName("test") )

  /**
    * map(func: RDD元素类型=> B ): 一对一映射
    *     map里面的方法是针对RDD每个元素操作,返回一个结果,rdd有多少元素,函数就调用多少次
    *     map生成的新的RDD中元素个数 = 原RDD元素个数
    *     map的应用场景: 对数据进行值/类型转换
    *     spark map算子是针对每个分区并行计算
    */
  @Test
  def map(): Unit ={

    val rdd = sc.parallelize(List(1,4,3,2,3,6,8,10))

    val rdd2 = rdd.map(x=>{
      println(s"${Thread.currentThread().getName} -- ${x}")
      x * x
    })

    //Executor task launch worker for task 0 -- 1
    //Executor task launch worker for task 0 -- 4
    //Executor task launch worker for task 1 -- 3
    //Executor task launch worker for task 1 -- 2
    //Executor task launch worker for task 3 -- 8
    //Executor task launch worker for task 3 -- 10
    //Executor task launch worker for task 2 -- 3
    //Executor task launch worker for task 2 -- 6

    val result = rdd2.collect()
    println(result.toList)
  }

  /**
    * spark算子里面的函数是在Executor执行的
    * spark算子外面的代码是在Driver执行的
    *
    * mapPartitions(func: Iterator[Int] => Iterator[B]): 映射
    * mapPartitions里面的函数是针对RDD每个分区操作,rdd有多少分区,函数就调用多少次
    * mapPartitions生成新的RDD,新RDD的分区的元素是由函数返回的
    *
    * map与mapPartitions的区别:
    *     1、函数的参数类型不一样
    *         map里面的函数的参数类型就是RDD元素类型,RDD元素有多少个,函数就调用多少次
    *         mapPartitions里面的函数的参数是Iterator[RDD元素类型], RDD分区有多少个,函数就调用多少次
    *     2、函数的返回值不一样
    *         map里面的函数是一个元素返回一个结果，map里面的函数的返回值没有规定类型, map生成的新的RDD的元素个数  = 原RDD元素个数
    *         mapPartitions里面的函数是一个迭代器返回新的迭代器, mapPartitions生成的新的RDD元素个数可能与原RDD元素个数不太一样
    *     3、元素内存回收时机不一样
    *         map里面的函数是针对单个元素操作,元素操作完毕之后,当前元素内存就可以回收了
    *         mapPartitions里面的函数是针对一个分区的迭代器操作,此时单个元素操作完成, 此时元素不能立即回收，必须等到整个迭代器操作完成之后才能统一回收。所以可能出现内存溢出,可以用map代替。
    *
    */
  @Test
  def mapPartition(): Unit ={

    val rdd = sc.parallelize(List(1,6,4,3,8,10))
    //rdd装的是用户id

    //用户的数据放在mysql中



    //需求: 获取每个用户的(姓名，年龄，id)
    val rdd2 = rdd.map(id=>{

      var connection:Connection = null
      var statement:PreparedStatement = null

      try{
        connection = DriverManager.getConnection("jdbc:mysql://hadoop102:3306/test","root","123456")
        println(connection)
        statement = connection.prepareStatement("select * from user where id=?")

        statement.setInt(1,id)

        val resultSet = statement.executeQuery()

        var name:String = null
        var age:Int = 0
        while(resultSet.next()){
          name = resultSet.getString("name")
          age = resultSet.getInt("age")

        }

        (id,name,age)
      }catch {
        case e:Exception=>
          e.printStackTrace()
          (id,null,0)
      }finally {
        if(statement!=null)
          statement.close()
        if(connection!=null)
          connection.close()

      }

    })

    println(rdd2.collect().toList)


    val rdd3 = rdd.mapPartitions( it => {

      var connection:Connection = null
      var statement:PreparedStatement = null
      var result:List[(Int,String,Int)] = Nil
      try{
        connection = DriverManager.getConnection("jdbc:mysql://hadoop102:3306/test","root","123456")
        statement = connection.prepareStatement("select * from user where id=?")
        println(connection)
        it.foreach(id=>{
          statement.setInt(1,id)
          val resultSet = statement.executeQuery()

          var name:String = null
          var age:Int = 0
          while(resultSet.next()){
            name = resultSet.getString("name")
            age = resultSet.getInt("age")
          }
          result = (id,name,age) :: result

        })

      }catch {
        case e:Exception=>
          e.printStackTrace()
      }finally {
        if(statement!=null)
          statement.close()
        if(connection!=null)
          connection.close()

      }


      result.toIterator
    } )

    println("---->"+rdd3.collect().toList)
  }

  /**
    * mapPartitionsWithIndex( func: ( Int, Iterator[RDD元素类型] ) => Iterator[B] )
    *     mapPartitionsWithIndex里面的函数第一个参数是分区号,第二个参数第该分区数据的迭代器
    *     mapPartitionsWithIndex与mapPartitions的区别:
    *         mapPartitions的函数只有一个参数,参数是就是分区数据的迭代器
    *         mapPartitionsWithIndex的函数相比mapPartitions里面的函数多了一个分区号
    */
  @Test
  def mapPartitionsWithIndex(): Unit ={
    val rdd = sc.parallelize(List(1,6,4,3,8,10))

    val result = rdd.mapPartitionsWithIndex( (index, it) => {
      println(s"index=${index} data=${it.toList}")
      val a = it //it是Iterator 只能遍历一次
      it.filter(_%2==0)
    } ).collect()

    println(result.toList)  // 所以该行结果空值
  }

  /**
    * flatMap(func: RDD元素类型=> 集合) = map + flatten
    *     flatMap里面的函数是针对RDD每个元素操作,元素有多少个,函数就调用多少次
    *     flatMap的应用场景: 一对多
    */
  @Test
  def flatMap(): Unit ={

    val rdd = sc.parallelize(List("hello","spark","java"))

    val rdd2 = rdd.flatMap(x=>x)

    println(rdd2.collect().toList)
//    println(rdd2.collect().toList.groupBy(x=>x).map(x=>x._2(0))) //groupBy(x=>x).map(x=>x._2(0)) 相当于去重 distinct
  }

  /**
    * groupBy(func: RDD元素类型=> K ): 按照指定字段分组
    *     groupBy里面的函数也是针对单个元素操作
    *     groupBy会生成一个新的RDD,新RDD的元素是KV键值对,K是函数返回值,V是key在原RDD中对应的所有元素
    *
    * MR的执行过程: 数据->InputFormat->map->写入环形缓冲区[80% 分组排序] -> [combiner] ->溢写 -> 合并小文件 -> reduce拉取数据 -> 归并排序 -> reduce -> outputformat -> 磁盘
    * MR shuffle阶段: 写入环形缓冲区[80% 分组排序] -> [combiner] ->溢写 -> 合并小文件 -> reduce拉取数据 -> 归并排序
    * spark shuffle阶段: 写入缓冲区[分组 [排序] ] -> [combiner] ->溢写 -> 合并小文件 -> 分区拉取数据 -> 归并[排序]
    *
    */
  @Test
  def groupBy(): Unit ={

    val rdd = sc.parallelize(List( ("lisi","man","beijing"),("wangwu","woman","shenzhen"),("zhaoliu","man","shenzhen") ))

    val rdd2 = rdd.groupBy(x=> x._3)

    println(rdd2.collect().toList)
  }

  /**
    * filter(func: RDD元素类型=>Boolean ): 按照指定条件过滤
    *   filter里面的函数是针对单个元素操作
    *   filter保留的是函数返回值为true的数据
    */
  @Test
  def filter(): Unit ={
    val rdd = sc.parallelize(List(1,6,4,3,8,10))

    val rdd2 = rdd.filter(x=> x%2==0 )

    println(rdd2.collect().toList)
  }

  /**
    * glom(): 将一个分区的所有数据转成一个数组元素
    *   glom生成的新的RDD的元素个数 = 原RDD分区数
    */
  @Test
  def glom(): Unit ={
    val rdd = sc.parallelize(List(1,6,4,3,8,10))

    rdd.mapPartitionsWithIndex((index,it)=>{
      println(s"index=${index} data=${it.toList}")
      it
    }).collect()

    val rdd2 = rdd.glom()
    
    val array = rdd2.collect()

//    val flatten: List[Int] = array.flatten.toList
//    println(flatten)

    array.foreach(x=>println(x.toList))

  }

  /**
    * sample(withReplacement,fraction[,seed]): 采样
    *     withReplacement: 同一个元素是否可以被多次采样 <工作中一般设置为false>
    *     fraction
    *         withReplacement=false, fraction代表每个元素被采样的概率[0,1] <工作中一般设置为0.1-0.2左右>
    *         withReplacement=true, fraction代表每个元素期望被采样的次数
    *sample在工作中一般只用于数据倾斜场景。  通过采样的数据映射整个数据集的情况
    *出现数据倾斜之后,一般先通过sample采样数据,再统计采样数据中每个key的个数从而得知哪个key出现了数据倾斜.,
    *
    */
  @Test
  def sample(): Unit ={

    val rdd = sc.parallelize(List(1,4,2,3,7,9,8,11,33,22,66,55,44,99,88,100))

    val rdd2 = rdd.sample(true,10)

    println(rdd2.collect().toList)

    val rdd3 = rdd.sample(false,0.5)

    println(rdd3.collect().toList)
  }

  /**
    * 去重
    * distinct会产生shuffle操作
    */
  @Test
  def distinct(): Unit ={

    val rdd = sc.parallelize(List(1,4,3,7,3,1,9,10,2,4))

    val rdd2 = rdd.distinct()

    println(rdd2.collect().toList)

    val rdd3 = rdd.groupBy( x=>x , numPartitions = 10)
    //println(rdd3.collect().toList)

    val rdd4 = rdd3.map(_._1)
    println(rdd3.getNumPartitions)

    println(rdd4.collect().toList)
  }

  /**
    * coalesce: 合并分区
    *     coalesce: 默认只能合并分区,此时不会产生shuffle操作
    *     如果想要增大分区数,此时需要设置coalesce的shuffle参数=true,此时会使用shuffle重分区
    *
    */
  @Test
  def coalesce(): Unit ={

    val rdd1 = sc.parallelize(List(1,4,3,7,3,1,9,10,2,4))

    rdd1.mapPartitionsWithIndex((index,it)=>{
      println(s"index=${index} data=${it.toList}")
      it
    }).collect()
    val rdd2 = rdd1.coalesce(10,true)
    val rdd3 = rdd1.coalesce(3)
    rdd3.mapPartitionsWithIndex((index,it)=>{
      println(s"index=${index} data=${it.toList}")
      it
    }).collect()
    println(rdd2.getNumPartitions)
    println(rdd3.getNumPartitions)
  }

  /**
    * repartition: 重分区
    *     repartition默认既可以增大分区也可以减少分区,但是都会产生shuffle
    *     repartition底层就是coalesce(..,shuffle=true)
    *
    * repartition与coalesce的区别:
    *       coalesce默认只能合并分区,此时不会产生shuffle操作，如果想要增大分区数,此时需要设置coalesce的shuffle参数=true,此时会使用shuffle重分区 <如果想要减少分区推荐使用coalesce,一般是搭配filter使用,可以避免shuffle>
    *       repartition默认既可以增大分区也可以减少分区,但是都会产生shuffle <如果想要增大分区推荐使用repartition,因为更简单>
    */
  @Test
  def repartition(): Unit ={
    val rdd = sc.parallelize(List(1,4,3,7,3,1,9,10,2,4))

    val rdd3 = rdd.repartition(2)

    val rdd4 = rdd.repartition(10)

    println(rdd3.getNumPartitions)
    println(rdd4.getNumPartitions)

  }

  /**
    * sortBy(func: RDD元素类型=> K ): 按照指定字段排序
    *     sortBy里面的函数是针对RDD单个元素操作
    *     sortBy是根据函数的返回值进行排序
    *sortBy会产生shuffle操作，是全局排序
    *
    */
  @Test
  def sortBy(): Unit ={

    val rdd = sc.parallelize(List(1,4,3,7,3,1,9,10,2,4))

    val rdd2 = rdd.sortBy( x => x,false )

    println(rdd2.collect().toList)

    Thread.sleep(1000000)
  }

  /**
    * pipe: 调用外部的脚本
    *   pipe会生成新的RDD,新RDD的元素是在脚本中通过echo返回的
    *   pipe调用脚本是每个分区调用一次,每次调用的时候会将分区所有数据当做参数传给脚本
    */
  @Test
  def pipe(): Unit ={
    
  }
}
