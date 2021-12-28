package day11OOP.src.com.atguigu.interface1.exer2;

public interface A {
    int x = 0;
}
class B{
    int x = 1;
}
class C extends B implements A{
    public void printX(){
        System.out.println(A.x);
    }
}
