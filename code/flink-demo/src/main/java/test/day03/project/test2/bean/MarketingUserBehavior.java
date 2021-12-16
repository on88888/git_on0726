package test.day03.project.test2.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月11日 14:40:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//创建JavaBean类
public class MarketingUserBehavior {
    private Long userId;
    private String behavior;
    private String channel;
    private Long timestamp;
}