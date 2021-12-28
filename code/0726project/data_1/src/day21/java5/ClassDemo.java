package day21.java5;

/*
    获取Class类的对象的四种方式:

    说明:
        1.Class是反射的源头(没有Class类的对象就没有办法使用反射)
        2.Class类的对象就是加载到内存的字节码文件.
 */
public class ClassDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        //方式一: 类.class
        Class clazz = Person.class;
        //方式二 : 对象名.getClass()
        Person person = new Person();
        Class clazz2 = person.getClass();
        //方式三 : Class.forName("类的全类名")  全类名:包括包名在内的类的全名称
        Class clazz3 = Class.forName("com.atguigu.java5.Person");
        //方式四 : 通过类加载器
        Class clazz4 = Person.class.getClassLoader()
                .loadClass("com.atguigu.java5.Person");
        System.out.println(clazz == clazz2 && clazz3 == clazz4 && clazz == clazz4);
    }
}

class Person{

}