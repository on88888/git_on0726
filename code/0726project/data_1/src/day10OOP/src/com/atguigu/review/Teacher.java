package day10OOP.src.com.atguigu.review;

public class Teacher extends Person{

    public Teacher() {
    }

    public Teacher(String name, int age, char sex, double salary) {
        super(name, age, sex);
        this.salary = salary;
    }

    private double salary;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    @Override
    public String getInfo() {
        return super.getInfo()+",薪水："+salary;
    }
}
