package day11OOP.src.com.atguigu.poly.exer1;

public class HourEmployee  extends Employee{
    int workHours;
    int price;

    public HourEmployee(String name, MyDate birthday, int workHours, int price) {
        super(name, birthday);
        this.workHours = workHours;
        this.price = price;
    }

    @Override
    public double earning() {
        return workHours*price;
    }
    @Override
    public String getInfo() {
        return super.getInfo()+",时薪："+price+",工作小时数："+workHours;
    }
    public void leave(){
        System.out.println("离开.....");
    }
}
