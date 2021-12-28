package day11OOP.src.com.atguigu.interface1.i4;

public class Student extends Man implements C {

}

class Man {

    public void cc(){
        System.out.println("this is man cc");
    }

}
interface C{

    public default void cc(){
        System.out.println("this is C cc");
    }
}