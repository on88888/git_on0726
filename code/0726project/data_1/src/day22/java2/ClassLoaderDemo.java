package day22.java2;

import org.junit.Test;

public class ClassLoaderDemo {

    /*
        验证不同的类使用的类加载器
     */
    @Test
    public void test2() throws ClassNotFoundException {
        ClassLoader classLoader =
                Class.forName("java.lang.String").getClassLoader();
        System.out.println(classLoader);
    }

    /*
    sun.misc.Launcher$AppClassLoader@18b4aac2
    sun.misc.Launcher$ExtClassLoader@4a574795
    null

     */
    @Test
    public void test(){
        //应用程序类加载器 ：加载自定义类
        ClassLoader classLoader = this.getClass().getClassLoader();
        System.out.println(classLoader);
        //扩展类加载器 ：加载扩展类
        ClassLoader parent = classLoader.getParent();
        System.out.println(parent);
        //获取引导类加载器 ：加载核心类库中的类
        ClassLoader parent2 = parent.getParent();
        System.out.println(parent2);
    }
}
