package day21.java6;

import org.junit.Test;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectDemo {
    /*
        通过反射获取属性
     */
    @Test
    public void test() throws NoSuchFieldException, IllegalAccessException {
        Student student = new Student();

        //1.先获取运行时类的对象
        Class clazz = Student.class;
        //2.获取属性
        /*
            getField(String name) : 获取指定属性(只能是public修饰的)
            name : 属性的名字
         */
        Field sname = clazz.getField("sname");
        /*
        set(Object obj, Object value)
        obj : 给哪个对象的该属性赋值
        value : 属性的值
         */
        sname.set(student,"longge");

        //================================给私有(所有权限都可以)属性赋值===================
        //获取指定属性
        Field sage = clazz.getDeclaredField("sage");
        //允许访问
        sage.setAccessible(true);
        //给属性赋值
        sage.set(student,120);

        student.run();
    }

    /*
        通过反射获取所有的属性
     */
    @Test
    public void test2(){
        //1.获取运行时类的对象
        Class clazz = Student.class;
        //2.获取所有的属性(public) -- 子类及父类中所有public修饰的属性
        Field[] fields = clazz.getFields();
        //3.遍历
        for (Field f: fields) {
            System.out.println(f);
        }

        //=====================获取本类中所有的属性=====================
        System.out.println("===============================================");
        //getDeclaredFields() :获取本类中所有的属性
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields) {
            System.out.println(f);
        }

    }

    /*
        通过反射获取方法
     */
    @Test
    public void test3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Student student = new Student();

        //1.获取运行时类的对象
        Class clazz = Student.class;
        //2.获取指定的方法(public)
        /*
        getMethod(String name, Class<?>... parameterTypes)
        name : 方法的名字
        parameterTypes : 形参的类型
         */
        Method publicTest = clazz.getMethod("publicTest",String.class,int.class);
        //调用方法
        /*
        invoke(Object obj, Object... args)
        obj : 通过哪个对象调用该方法
        args : 实参
         */
        publicTest.invoke(student,"fanlaoshi",20);

        //======================调用私有方法(所有权限)================
        //获取指定方法
        Method privateTest = clazz.getDeclaredMethod("privateTest");
        //允许访问
        privateTest.setAccessible(true);
        //调用方法
        privateTest.invoke(student);
    }

    /*
        通过反射获取所有方法
     */
    @Test
    public void test4(){
        //1.获取运行时类的对象
        Class clazz = Student.class;
        //2.获取所有的方法(子类及父类中所有的public修饰的方法)
        Method[] methods = clazz.getMethods();
        //3.遍历
        for (Method m : methods) {
            System.out.println(m);
        }

        //======================获取本类中所有的方法===================\
        System.out.println("===============================================");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods) {
            System.out.println(m);
        }
    }
}
