package day22.java;

import org.junit.Test;

import java.lang.reflect.*;
import java.util.Arrays;

/*
    反射需要掌握的 ： 1.获取Class的四种方式  2.通过反射获取属性和方法
            3.理解运行时类    4.理解类加载过程   5.理解类加载器

    了解即可
 */
public class ReflectDemo {

    /*
        通过反射获取构造器并创建对象
     */
    @Test
    public void test() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //1.获取Class对象
        Class clazz = Student.class;
        //2.获取构造器
        /*
            getConstructor(Class<?>... parameterTypes)
            parameterTypes : 形参的类型
         */
        Constructor constructor = clazz.getConstructor(int.class);
        //3.创建对象
        /*
        newInstance(Object ... initargs)
        initargs : 实参
         */
        constructor.newInstance(10);
    }
    @Test
    public void test2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //1.获取Class对象
        Class clazz = Student.class;
        //默认通过该类的空参构造器创建对象
        // （前提是必须有空参构造器-如果没有空参构造器需要先获取构造器再创建对象）
        clazz.newInstance();
    }

    /*
        通过反射获取父类
     */
    @Test
    public void test3(){
        Class clazz = Student.class;
        //获取父类
        Class superclass = clazz.getSuperclass();
        System.out.println(superclass);
    }
    /*
        通过反射获取属性的详情
     */
    @Test
    public void test4(){
        Class clazz = Student.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields) {
            //获取属性的权限修饰符
            System.out.print(Modifier.toString(f.getModifiers()) + " ");
            //获取属性的类型
            System.out.print(f.getType() + " ");
            //获取属性的名字
            System.out.println(f.getName());
            System.out.println();
        }
    }

    /*
        通过反射获取方法的详情
     */
    @Test
    public void test5(){
        Class clazz = Student.class;

        Method[] declaredMethods = clazz.getDeclaredMethods();

        for (Method m : declaredMethods) {
            //获取方法的权限修饰符
            System.out.print(Modifier.toString(m.getModifiers()) + " ");
            //获取方法的返回值类型
            System.out.print(m.getReturnType() + " ");
            //获取方法的名字
            System.out.print(m.getName() + " ");
            //获取形参
            Class[] parameterTypes = m.getParameterTypes();
            System.out.println(Arrays.toString(parameterTypes));

            System.out.println();
        }
    }
}
