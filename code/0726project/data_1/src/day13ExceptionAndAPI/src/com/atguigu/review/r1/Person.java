package day13ExceptionAndAPI.src.com.atguigu.review.r1;

class Person{
        
        public void show(){
            System.out.println("this is Person");
        }
    }
class Student extends Person{
    public void show(){
        System.out.println("this is Student");

    }
    public void eat(){
        System.out.println("Student eat");

    }

}