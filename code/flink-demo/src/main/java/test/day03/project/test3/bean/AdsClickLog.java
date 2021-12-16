package test.day03.project.test3.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月11日 15:15:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdsClickLog {
    private Long userId;
    private Long adId;
    private String province;
    private String city;
    private Long timestamp;
}
