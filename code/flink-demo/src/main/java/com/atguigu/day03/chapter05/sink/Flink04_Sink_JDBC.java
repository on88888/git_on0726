package com.atguigu.day03.chapter05.sink;

import com.atguigu.day03.bean.WaterSensor;
import com.atguigu.day03.chapter05.source.RandomWatersensor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.connector.jdbc.JdbcStatementBuilder;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter05.sink
 * @since 2021/12/11 10:54
 */
public class Flink04_Sink_JDBC {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
        env.setParallelism(1);

        env.addSource(new RandomWatersensor())
                .addSink(JdbcSink
                        .sink("insert into sensor values (?,?,?)",
                                new JdbcStatementBuilder<WaterSensor>() {
                                    @Override
                                    public void accept(PreparedStatement preparedStatement,
                                                       WaterSensor waterSensor) throws SQLException {
                                        preparedStatement.setString(1, waterSensor.getId());
                                        preparedStatement.setLong(2, waterSensor.getTs());
                                        preparedStatement.setInt(3, waterSensor.getVc());
                                    }
                                },
                                JdbcExecutionOptions
                                        .builder()
                                        .withBatchIntervalMs(5)
                                        .withBatchSize(1000)
                                        .withMaxRetries(200)
                                        .build(),
                                new JdbcConnectionOptions
                                        .JdbcConnectionOptionsBuilder()
                                        .withUrl("jdbc:mysql://rm-uf6romwlno0rxu8bxvo.mysql.rds.aliyuncs.com:3306/flink?useSSL=false")
                                        .withUsername("root")
                                        .withPassword("1qaz!QAZ")
                                        .withDriverName("com.mysql.jdbc.Driver")
                                        .build()

                        ));

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}