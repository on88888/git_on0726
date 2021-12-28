package day10OOP.src.com.atguigu.poly.exer2;

public class Manager  extends SalaryEmployee{
        double comm;

    public Manager(String name, double salary, int workDays, int offDays, double comm) {
        super(name, salary, workDays, offDays);
        this.comm = comm;
    }

    @Override
    public double earning() {
        return super.earning()*(1+comm);
    }
}
