package test.day01;


import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * @author on
 * @Description 用批处理的方式处理有界流数据（文件）
 * @since 2021年12月08日 16:43:41
 */
public class BatchwordcountTest {
    public static void main(String[] args) throws Exception {

        // 1. 创建执行环境(批处理的执行环境）来处理的是有界流
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSource<String> wordDS = env.readTextFile("input/words.txt");

        // 2. 处理数据
        wordDS
            // 经过flatmap之后 我们的数据由一行一行的 变成了一个一个的单词
                .flatMap(new FlatMapFunction<String, String>() {
                    @Override
                    public void flatMap(String value, Collector<String> out) throws Exception {
                        for (String word : value.split(" ")) {
                            out.collect(word);
                        }
                    }
                })
            // 经过map之后 我们的一个个的单词变成了(word,1L)这种形式
                .map(new MapFunction<String, Tuple2<String,Integer>>() {
                    @Override
                    public Tuple2<String, Integer> map(String word) throws Exception {
                        return Tuple2.of(word,1);
                    }
                })
            // 根据word进行分组
                .groupBy(0)
            // 根据第二个信息进行求和
                .sum(1)
            // 3. 打印
                .print();
    }
}
