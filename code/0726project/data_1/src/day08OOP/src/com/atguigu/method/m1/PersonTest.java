package day08OOP.src.com.atguigu.method.m1;
/*
静态方法 不能【直接】调用非静态资源(属性(成员变量) 方法),
        静态资源随着类的加载而加载 非静态的资源需要创建对象后才能分配空间，因此当静态资源加载完毕后 非静态资源可能还没有产生，使用时不能调用一个未产生的资源
        可以先创建对象 再调用 非静态的资源
非静态方法 直接使用所有的资源
       非静态方法被执行时  静态资源肯定已经完成空间开辟  所以可以使用已经存在的资源

 */
public class PersonTest {
    public static void main(String[] args) {

        System.out.println("Person.country = " + Person.country);
        Person.staticMethod2();
        Person.staticMethod1();
        Person p1 = new Person();
        System.out.println("p1.age = " + p1.age);
        p1.method1();
    }
}
