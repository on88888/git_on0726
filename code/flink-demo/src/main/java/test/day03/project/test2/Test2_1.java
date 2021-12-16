package test.day03.project.test2;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月11日 14:39:52
 */

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import test.day03.project.test2.bean.MarketingUserBehavior;


/**
 * 市场营销商业指标统计分析
 * 随着智能手机的普及，在如今的电商网站中已经有越来越多的用户来自移动端，
 * 相比起传统浏览器的登录方式，手机APP成为了更多用户访问电商网站的首选。
 * 对于电商企业来说，一般会通过各种不同的渠道对自己的APP进行市场推广，
 * 而这些渠道的统计数据（比如，不同网站上广告链接的点击量、APP下载量）就成了市场营销的重要商业指标。
 *
 * 求：APP通过每个渠道每种行为的数量；
 * 即通过小米下载了多少，通过华为商城下载了多少，通过ios store下载了多少…
 */

//APP市场推广统计-渠道+行为
public class Test2_1 {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port",10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        env.addSource(new MockAppDataSource())
                .map(new MapFunction<MarketingUserBehavior, Tuple2<String,Long>>() {
                    @Override
                    public Tuple2<String, Long> map(MarketingUserBehavior value) throws Exception {
                        return Tuple2.of(value.getChannel()+"\t"+value.getBehavior(), 1L);
                    }
                })
                /*.keyBy(new KeySelector<Tuple2<String, Long>, Object>() {
                    @Override
                    public Object getKey(Tuple2<String, Long> value) throws Exception {
                        return value.f0;
                    }
                })*/
                .keyBy(value -> value.f0)
                .sum(1)
                .print();

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
