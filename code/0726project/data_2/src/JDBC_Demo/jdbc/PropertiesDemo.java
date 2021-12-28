package JDBC_Demo.jdbc;

import org.junit.Test;

import java.io.FileInputStream;
import java.util.Properties;

/*
    1.Properties是Hashtable的子类。
    2.Properties是一个集合用来存放K,V (setProperty()K,V都是String类型)
    3.可以用来读取配置文件中的内容
 */
public class PropertiesDemo {
    @Test
    public void test(){
        Properties properties = new Properties();
        properties.setProperty("","");
    }

    /*
        3.可以用来读取配置文件中的内容
     */
    @Test
    public void test2() throws Exception {
        //1.创建Properties对象
        Properties properties = new Properties();
        //2.创建流
        FileInputStream fis = new FileInputStream("src\\JDBC_Demo\\jdbc.properties");
        //3.加载流
        properties.load(fis);
        //4.获取配置文件中的内容
        String name = properties.getProperty("name");
        String age = properties.getProperty("age");
        System.out.println(name + " " + age);

        //5.关资源
        fis.close();
    }
}
