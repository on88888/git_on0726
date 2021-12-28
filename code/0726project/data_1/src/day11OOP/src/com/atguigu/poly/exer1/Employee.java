package day11OOP.src.com.atguigu.poly.exer1;

public class Employee {
    private String name;

    private MyDate birthday;

    public MyDate getBirthday() {
        return birthday;
    }

    public Employee() {
    }

    public Employee(String name, MyDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public double earning(){
        return 0;
    }
    public String getInfo(){
        return "姓名："+name+",实发工资："+earning();
    }


    public String getName() {
        return name;
    }
}
class MyDate{
    int year;
    int day;
    int month;

    public MyDate(int year, int day, int month) {
        this.year = year;
        this.day = day;
        this.month = month;
    }
}