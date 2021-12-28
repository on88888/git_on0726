package day11OOP.src.com.atguigu.poly.exer1;

public class SalaryEmployee extends Employee {

    double salary;
    int workDays;
    int offDays;//请假天数

    public SalaryEmployee(String name, MyDate birthday, double salary, int workDays, int offDays) {
        super(name, birthday);
        this.salary = salary;
        this.workDays = workDays;
        this.offDays = offDays;
    }

    @Override
    public double earning() {
        return salary-salary/workDays*offDays;
    }

    @Override
    public String getInfo() {
        return super.getInfo()+",月薪："+salary+",工作日天数："+workDays+",请假天数："+offDays;
    }
}
