package test.day02.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月10日 18:13:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaterSensorTest {
    private String id;
    private Long ts;
    private Integer vc;
}
