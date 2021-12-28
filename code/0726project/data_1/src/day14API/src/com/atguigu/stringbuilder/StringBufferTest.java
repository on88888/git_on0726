package day14API.src.com.atguigu.stringbuilder;

import org.junit.Test;
/*
StringBuilder: 可变的字符序列
               父亲 AbstractStringBuilder
               底层采用 char[] 存储数据 默认长度为16
StringBuffer: 可变的字符序列
               父亲 AbstractStringBuilder
               底层采用 char[] 存储数据 默认长度为16
               区别：
                  StringBuffer 线程安全的  效率低
                  StringBuilder 线程不安全的  synchronized  效率高
 */
public class StringBufferTest {

    @Test
    public void test04() {
        StringBuffer builder = new StringBuffer();
        builder.append("ABCDEFG");
        System.out.println("builder = " + builder);
        /*
        将指定范围的数据替换为指定的内容
        replace(开始下标, 结束下标, "你好世界");
        [开始下标, 结束下标)
         */
        builder.replace(0, 4, "你好世界");
        System.out.println("builder = " + builder);

    }


    @Test
    public void test03() {

        StringBuffer builder = new StringBuffer();
        builder.append("ABCDEFG");
        System.out.println("builder = " + builder);
        //元素反转
        builder.reverse();

        System.out.println("builder = " + builder);


    }


    @Test
    public void test02() {

        StringBuffer builder = new StringBuffer();

        builder.append("ABCDEFG");
        System.out.println("builder = " + builder);
        //在指定位置插入数据
        builder.insert(1, "你好世界");
        System.out.println("builder = " + builder);//A你好世界BCDEFG

        //删除指定位置的数据
        builder.deleteCharAt(3);

        System.out.println("builder = " + builder);//A你好界BCDEFG
        /*
        删除指定范围数据
        delete(开始下标,结束下标)
        [开始下标,结束下标)
         */
        builder.delete(1,5);


        System.out.println("builder = " + builder);//ACDEFG

        //修改指定下标的值
        builder.setCharAt(3, '你');
        System.out.println("builder = " + builder);

        builder.append("你");
        System.out.println("builder = " + builder);// ACD你FG你
        //返回指定的元素第一次出现的下标
        int indexOf = builder.indexOf("你");
        System.out.println("indexOf = " + indexOf);

        int lastIndexOf = builder.lastIndexOf("你");
        System.out.println("lastIndexOf = " + lastIndexOf);
    }


    @Test
    public void test01(){

        StringBuffer builder = new StringBuffer();
        //追加数据
        builder.append("AB");
        System.out.println("builder = " + builder);

        builder.append("CD");
        System.out.println("builder = " + builder); //ABCD
        //获取字符数量
        System.out.println("builder.length() = " + builder.length());
//设置字符数量
        builder.setLength(2);

        System.out.println("builder.length() = " + builder.length());
        System.out.println("builder = " + builder);

    }
}
