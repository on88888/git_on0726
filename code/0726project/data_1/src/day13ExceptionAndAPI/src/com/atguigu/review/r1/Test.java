package day13ExceptionAndAPI.src.com.atguigu.review.r1;

class Test{
    
    public static void main(String [] args){
            // p 是Person的匿名子类
        Person p =     new Person(){};
        p.show();
           //Student 是person的  有名字的子类
         Student s = (Student)p;
    }
    
}