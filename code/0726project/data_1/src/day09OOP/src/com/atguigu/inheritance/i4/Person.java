package day09OOP.src.com.atguigu.inheritance.i4;

public class Person {

    String name;

    public Person getSum(){
        return null;
    }

      void show(){
        System.out.println("名字是："+name);
    }


    public void test(Person n){

    }

    public static void show1(){
        System.out.println("Person this is static show1() ");
    }


}
class Student extends Person{
  /*  public static void show1(){
        System.out.println("Student this is static show1() ");
    }*/

    public void test(Person n){

    }
     double score;



    public Person getSum() {
        return super.getSum();
    }

    public  void show(){
        System.out.println("名字是："+name+"，分数是："+score);
    }
}
class A extends Person{}