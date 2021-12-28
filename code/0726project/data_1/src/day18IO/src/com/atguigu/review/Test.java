package day18IO.src.com.atguigu.review;

public class Test {

    public static void main(String[] args) {
        // ? 代表任意类型 java中有很多类型
        Student<?> s1 = new Student<>();
        /*
        ? 可以放Number 或者是Number的孩子
         */
        Student<? extends Number> s2 = new Student<>();
       // s2.setScore(100);
        /*
        Number 或者是 Number的父亲
        父类类型作为泛型 传入子类对象
         */
        Student<? super Number> s3 = new Student<>();
        s3.setScore(100);//Intger
        s3.setScore(3.14);//Double



        show(new Animal());
        show(new Dog());
        show(new Cat());


    }

    public static void show(Animal a){


    }
}

class Animal{


}
class Dog  extends Animal{}

class Cat extends Animal{

}




class Student<T>{

    String name;
    T score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getScore() {
        return score;
    }

    public void setScore(T score) {
        this.score = score;
    }
}
