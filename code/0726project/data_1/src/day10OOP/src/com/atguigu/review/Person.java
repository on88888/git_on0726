package day10OOP.src.com.atguigu.review;

public class Person {
    private String name;
    private int age;
    private char sex;

    public Person(){}

    public Person(String name, int age, char sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getInfo(){
        return "姓名："+name+",年龄："+age+",性别："+sex;
    }
}
