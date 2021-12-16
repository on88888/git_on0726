package com.atguigu.logger;

import org.apache.log4j.Logger;

/**
 * Created by Smexy on 2021/11/27
 *
 *      ①哪些程序运行时，需要进行日志打印
 *              Logger: 如果当前程序需要打印日志，必须先声明一个Logger
 *
 *              使用Logger在打印日志时，可以指定日志的优先级
 *                      FATAL : 致命错误
 *                      ERROR:  普通错误
 *                      WARN :  警告
 *                      INFO  : 普通信息
 *                      DEBUG ： 调试信息
 *                      TRACE :  不放过任何信息
 *
 *                java代码中定义！
 *
 *      ②如果需要打印日志，如何打
 *              日志打印到哪里？ 控制台？ 文件中？ 网络发送给某个端口...
 *                  Appender ：  追加器，日志输出器
 *                      ConsoleAppender： 打印到控制台
 *                      FileAppender： 打印到文件
 *
 *              日志打印时，每一行用什么格式取打？
 *
 *                  Layout : 样式。
 *                      PatternLayout ： 自定义格式，按照指定的格式输出
 *
 *
 *                  在配置文件中定义！
 *
 *
 *     ---------------------------------
 *      如何声明配置文件：
 *              名字固定：log4j.xml
 *                       log4j.properties
 *
 */
public class Log4jDemo1 {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger(Log4jDemo1.class);

        logger.info("info:aaaa");
        logger.warn("warn:aaaa");
        logger.fatal("fatal:aaaa");
        logger.debug("debug:aaaa");
        logger.error("error:aaaa");
        logger.trace("trace:aaaa");



    }

}
