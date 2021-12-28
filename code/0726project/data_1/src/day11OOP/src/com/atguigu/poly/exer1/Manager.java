package day11OOP.src.com.atguigu.poly.exer1;

public class Manager extends SalaryEmployee {

  double comm;

    public Manager(String name, MyDate birthday, double salary, int workDays, int offDays, double comm) {
        super(name, birthday, salary, workDays, offDays);
        this.comm = comm;
    }

    @Override
    public double earning() {
        return super.earning()*(1+comm);
    }

    @Override
    public String getInfo() {
        return super.getInfo()+",奖金比例："+comm;
    }
}
