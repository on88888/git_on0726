package day15APIAndList.src.com.atguigu.other;

import org.junit.Test;

import java.util.Arrays;
import java.util.Properties;

public class SystemTest {

    @Test
    public void test04(){
        //获取jdk 系统配置信息
        Properties properties = System.getProperties();
        //展现 Properties内所有的配置信息
        //properties.list(System.out);

        //通过指定的key 获取value
        String value = properties.getProperty("sun.jnu.encoding");
        System.out.println("value = " + value);
    }


    @Test
    public void test03(){

        //System.exit(0);
        //运行垃圾回收器
        System.gc();



    }



    @Test
    public void test02(){

        String [] arr = {"安琪拉","嬴政","米莱迪","王昭君"};


        String [] newArr = new String[6];

        System.out.println("Arrays.toString(newArr) = " + Arrays.toString(newArr));
        /*
        src: 源数组
        srcPos： 源数组开始复制下标
        dest: 目标数组
        destPos：目标数组开始粘贴下标
        length: 复制的数量
         */
        System.arraycopy(arr, 1, newArr, 2, 2);

        System.out.println("Arrays.toString(newArr) = " + Arrays.toString(newArr));






    }


    @Test
    public void test01(){
        //获取当前时间的毫秒数
        long millis = System.currentTimeMillis();

        System.out.println("millis = " + millis);


    }
}
