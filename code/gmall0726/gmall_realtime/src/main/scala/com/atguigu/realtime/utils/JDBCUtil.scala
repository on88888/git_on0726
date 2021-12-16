package com.atguigu.realtime.utils

/**
 * Created by Smexy on 2021/12/3
 */
import java.sql.{Connection, PreparedStatement, ResultSet}
import java.util.Properties
import com.alibaba.druid.pool.DruidDataSourceFactory
import javax.sql.DataSource
import org.apache.kafka.common.TopicPartition
import scala.collection.mutable

object JDBCUtil {

  private val properties: Properties = PropertiesUtil.load("db.properties")

  // 创建连接池对象
  var dataSource:DataSource = init()

  // 连接池的初始化
  def init():DataSource = {

    val paramMap = new java.util.HashMap[String, String]()
    paramMap.put("driverClassName", properties.getProperty("jdbc.driver.name"))
    paramMap.put("url", properties.getProperty("jdbc.url"))
    paramMap.put("username", properties.getProperty("jdbc.user"))
    paramMap.put("password", properties.getProperty("jdbc.password"))
    paramMap.put("maxActive", properties.getProperty("jdbc.datasource.size"))

    // 使用Druid连接池对象
    DruidDataSourceFactory.createDataSource(paramMap)
  }

  // 从连接池中获取连接对象
  def getConnection(): Connection = {
    dataSource.getConnection
  }

  def main(args: Array[String]): Unit = {

    println(getConnection())

  }

}
