package day10OOP.src.com.atguigu.poly.exer2;

public class Employee {
    String name;

    public Employee(String name) {
        this.name = name;
    }

    public double earning() {
        return 0;
    }
    public String getInfo(){
        return "姓名："+name+",实发工资："+earning();
    }

}
