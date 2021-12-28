package day14API.src.com.atguigu.review;

public class Person {
    private   String name;
    private  int age;
    private double salary;
    private char sex;

    public Person(String name, int age, double salary, char sex)  {
        this.name = name;
        this.age = age;
        this.salary = salary;
   //     this.sex = sex;
        try {
            setSex(sex);
        } catch (SexException e) {
           // e.printStackTrace();
            System.out.println("e.getMessage() = " + e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) throws SexException{
        if(sex=='男'|| sex=='女'){
            this.sex = sex;
        }else{
            throw new SexException("性别有误 "+sex);
        }

    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", sex=" + sex +
                '}';
    }
}
