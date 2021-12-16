package test.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 0726
 * @ClassName Employee
 * @createTime 2021年12月04日 11:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private String empid;
    private Integer age;
    private String gender;
    private String name;
    private String hobby;
    private Double balance;

}
