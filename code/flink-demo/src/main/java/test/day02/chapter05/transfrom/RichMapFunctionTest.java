package test.day02.chapter05.transfrom;


import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月10日 20:34:59
 */
public class RichMapFunctionTest {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10001);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
        env.setParallelism(2);

        DataStreamSource<Integer> ds = env.fromElements(1, 2, 3, 4);

        // 求出每个数字的平方然后传出来
        ds.map(new RichMapFunction<Integer, Integer>() {
                    @Override
                    public Integer map(Integer value) throws Exception {
                        return value * value;
                    }

                    @Override
                    public void open(Configuration parameters) throws Exception {
                        System.out.println("RichMapFunctionTest.open");
                    }

                    @Override
                    public void close() throws Exception {
                        System.out.println("RichMapFunctionTest.close");
                    }
                })
                .print();

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
