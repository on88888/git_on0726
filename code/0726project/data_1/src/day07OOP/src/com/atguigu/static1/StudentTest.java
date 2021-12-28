package day07OOP.src.com.atguigu.static1;

public class StudentTest {
    public static void main(String[] args) {
        //创建对象
        Student s1 = new Student();
        s1.name="张三";
        s1.age=18;
        s1.score=98.6;
        s1.country="中国";
        System.out.println("name = "+s1.name+",age = "+s1.age+", score= "+ s1.score+", country = "+s1.country);

        Student s2 = new Student();
        s2.name="李四";
        s2.age=19;
        s2.score=89.6;
    //    s2.country="中国";
        System.out.println("name = "+s2.name+",age = "+s2.age+", score= "+ s2.score+", country = "+s2.country);

        Student s3 = new Student();
        s3.name="王五";
        s3.age=20;
        s3.score=89.9;
     //   s3.country="中国";
        System.out.println("name = "+s3.name+",age = "+s3.age+", score= "+ s3.score+", country = "+s3.country);

    }
}
