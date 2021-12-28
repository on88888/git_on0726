package day09OOP.src.com.atguigu.encap.e3;

public class Student {

    private String name;
    private int age;
    private char sex;


    //设置性别
    public void setSex(char sex) {
        if (sex == '男' || sex == '女') {
            this.sex = sex;
        } else {
            System.out.println("您输入的性别有误....");
        }
    }

    //获取性别
    public char getSex() {
        return sex;
    }
    //设置年龄
    public void setAge(int age) {
        if (age >= 1 && age <= 150) {
            this.age = age;
        } else {
            System.out.println("您输入的年龄 不符合人类要求");
        }

    }

    //获取年龄
    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
