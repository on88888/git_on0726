package day10OOP.src.com.atguigu.poly.exer2;

public class SalaryEmployee extends Employee {
    double salary;
    int workDays;
    int offDays;

    public SalaryEmployee(String name, double salary, int workDays, int offDays) {
        super(name);
        this.salary = salary;
        this.workDays = workDays;
        this.offDays = offDays;
    }

    @Override
    public double earning() {
        return salary-salary/workDays*offDays;
    }
}
