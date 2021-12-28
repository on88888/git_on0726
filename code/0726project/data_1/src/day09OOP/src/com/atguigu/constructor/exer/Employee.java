package day09OOP.src.com.atguigu.constructor.exer;

public class Employee {

    private String id;
    private String name;
    private int salary;
    private char sex;

    public Employee() {
    }

    public Employee(String id, String name, int salary, char sex) {

        this.id= id;
        this.name=name;
        setSex(sex);
        setSalary(salary);
    }


    public void setSalary(int salary) {

        if (salary >= 10000 && salary <= 20000) {
            this.salary = salary;
        } else {
            System.out.println("薪资有问题");
        }
    }

    public int getSalary() {
        return salary;
    }

    public void setSex(char sex) {
        if(sex=='男'|| sex=='女'){
            this.sex = sex;
        }else{
            System.out.println("性别有误");
        }

    }

    public char getSex() {
        return sex;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public String getInfo(){
        return "name = "+name+",id = "+id+", salary = "+ salary+",sex = "+sex;
    }


}
