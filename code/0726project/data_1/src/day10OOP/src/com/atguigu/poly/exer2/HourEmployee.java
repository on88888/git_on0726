package day10OOP.src.com.atguigu.poly.exer2;

public class HourEmployee extends Employee {

    int workHours;
    int price;

    public HourEmployee(String name, int workHours, int price) {
        super(name);
        this.workHours = workHours;
        this.price = price;
    }

    @Override
    public double earning() {
        return workHours*price;
    }
}
