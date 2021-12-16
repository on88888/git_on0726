package test.day03.project.test2;

import org.apache.flink.streaming.api.functions.source.SourceFunction;
import test.day03.project.test2.bean.MarketingUserBehavior;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月11日 14:42:56
 */

//创建App行为数据源
public class MockAppDataSource implements SourceFunction<MarketingUserBehavior> {

    boolean running = true;
    Random random = new Random();

    List<String> channels = Arrays.asList("huawwei", "xiaomi", "apple", "baidu", "tencent", "oppo", "vivo");
    List<String> behaviors = Arrays.asList("download", "install", "update", "uninstall");
    @Override
    public void run(SourceContext<MarketingUserBehavior> ctx) throws Exception {
        while (running){
            MarketingUserBehavior marketingUserBehavior = new MarketingUserBehavior(
                    (long) random.nextInt(50000),
                    behaviors.get(random.nextInt(behaviors.size())),
                    channels.get(random.nextInt(channels.size())),
                    System.currentTimeMillis());
            ctx.collect(marketingUserBehavior);
            Thread.sleep(200);
        }
    }

    @Override
    public void cancel() {
        running = false;
    }
}
