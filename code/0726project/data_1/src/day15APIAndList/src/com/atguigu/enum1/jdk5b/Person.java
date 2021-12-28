package day15APIAndList.src.com.atguigu.enum1.jdk5b;
/*
jdk1.5之前实现枚举：
    1.构造器私有

    2. 创建有限的几个值

 */
public class Person {
    String name="张三";

    //char sex='A';

    Gender man = Gender.WOMAN;

}
//性别类
class Gender{
    public static final Gender MAN = new Gender("男");
    public static final Gender WOMAN = new Gender("女");

    String desc;


    private Gender(String desc) {
        this.desc = desc;


    }
}