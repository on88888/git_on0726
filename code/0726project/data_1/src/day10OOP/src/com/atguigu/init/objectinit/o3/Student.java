package day10OOP.src.com.atguigu.init.objectinit.o3;

class Student extends Person{
    {
        System.out.println("Student 代码块 4");
    }
    String name = getName();
    //子类重写了父类的方法
    public String getName(){
        System.out.println("Student getName() 5");
        return "";
    }

    public Student() {
        super();
        System.out.println("Student 无参 6");
    }
}