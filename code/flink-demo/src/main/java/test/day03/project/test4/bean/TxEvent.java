package test.day03.project.test4.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月11日 15:29:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TxEvent {
    private String txId;
    private String payChannel;
    private Long eventTime;
}