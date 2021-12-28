package day12OOPAndException.src.com.atguigu.comparable.c1;

public class Person implements Comparable {
    String name;
    int age;
    double salary;

    @Override
    public int compareTo(Object o) {//Object o = p2;
        //向下转型 获取子类独有资源
        Person p = (Person)o;
        //按照年龄比
     /*   if(this.age==p.age){
            return 0;
        }else if(this.age>p.age){
            return 1;
        }else{
            return -1;
        }*/

     /*  if(this.salary==p.salary){
         return 0;
     }else if(this.salary>p.salary){
         return 1;
     }else{
         return -1;
     }*/

        int compare = Double.compare(this.salary, p.salary);
        return compare;
        // return this.age-p.age;
       // return (int)(this.salary-p.salary);
    }

    public Person(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
