package day14API.src.com.atguigu.string.s1;

import org.junit.Test;

import java.util.Arrays;

/*
正则表达式：文本校验
        正则是一门独立的语言 与java是兄弟关系
[]匹配单个字符
    [AB] 可以匹配A , B
    [A-Z]
    [a-z]
    [A-z]
\w: 字母数字下划线
\d: 所有的数字

？：0次或1次
*： 0次或多次
+： 1次或多次

{n}: 重复n次

{n,m}: 重复[n,m]次

{n,}: 至少重复n次


^:
    [^BC]:只要不是BC就可以

    ^A:以A开头
$: 以xxx结尾

 */
public class Method3 {

    @Test
    public void test06(){
        String s = "1A22B33312C43214D5231215E6";
        //将开头结尾的数字替换为""
        String s1 = s.replaceAll("^\\d|\\d$","");
        System.out.println("s1 = " + s1); //A2B3C4D5E  //A22B33C44D55E
        String[] split = s1.split("\\d+");
        System.out.println("split.length = " + split.length);
        System.out.println("Arrays.toString(split) = " + Arrays.toString(split));
    }

    @Test
    public void test05(){

        String s = "D";

        boolean matches = s.matches("[^BC]");

        System.out.println("matches = " + matches);


    }


    @Test
    public void test04(){
        //1 35879
        String s = "23699175106";
        boolean matches = s.matches("1[35789]\\d{9}");
        System.out.println("matches = " + matches);
    }

    @Test
    public void test03(){

        String s ="11";

        //boolean matches = s.matches("[0-9]");
        boolean matches = s.matches("\\d+");
        System.out.println("matches = " + matches);


    }


    @Test
    public void test02(){
        String s = "1";
        boolean matches = s.matches("\\w");
        System.out.println("matches = " + matches);
    }


    @Test
    public void test01(){
        String s = "B";
        boolean matches = s.matches("[AB]");
        System.out.println("matches = " + matches);
    }
}
