package org.atguigu.gmall_logger.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.gmall.constants.TopicName;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Smexy on 2021/11/27
 *
 *      客户端发日志的url是什么！
 *              去造数据jar包的配置文件中查看！
 *
 *      日志的请求参数名叫什么？
 *               param
 *
 *
 *          http://localhost:8888/gmall_logger/log?param=日志
 *
 *     -----------------------
 *        生成的消息封装的java类型：  ProducerRecord<key,value>
 *                  key仅仅用来分区！
 *                  value是存储数据！
 *                  value不能为null，但是key可以为null，为nullkafka会使用轮询的策略将数据存入不同的分区！
 *                  如果希望把同一批value存入同一个分区，保证这些value的key必须是一样的！
 *
 *
 *         ----------
 *         119
 */
@RestController
@Log4j //Logger log = Logger.getLogger(LogController.class);
public class LogController {
    //KafkaProducer<String, String> producer = new KafkaProducer<>(new Properties());
    @Autowired
    private KafkaTemplate<String,String> client;

    /*
        http://localhost:8888/gmall_logger/log

        / 在.java中解析为   http://localhost:8888/gmall_logger/
     */
    @RequestMapping(value = "/log")
    public Object handle1(String param){

        //System.out.println(param);
        log.info(param);

        //将string 转换为 java对象
        JSONObject jsonObject = JSON.parseObject(param);

        if (jsonObject.containsKey("start")){

                client.send(TopicName.STARTUP_LOG , param);

        }

        if (jsonObject.containsKey("page")){

            client.send(TopicName.PAGE_LOG , param);

        }

        if (jsonObject.containsKey("displays")){

            client.send(TopicName.DISPLAY_LOG , param);

        }

        if (jsonObject.containsKey("actions")){

            client.send(TopicName.ACTIONS_LOG , param);

        }

        if (jsonObject.containsKey("err")){

            client.send(TopicName.ERROR_LOG , param);

        }

        return  "success";


    }


    @RequestMapping(value = "/hello")
    public Object handle2(){

        System.out.println("hello被处理了");

        return "sueecee";

    }
}
