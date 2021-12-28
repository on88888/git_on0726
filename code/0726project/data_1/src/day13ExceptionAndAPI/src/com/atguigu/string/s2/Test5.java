package day13ExceptionAndAPI.src.com.atguigu.string.s2;

import org.junit.Test;

import java.util.Arrays;

/*
A-z

"BBBCCCZZZZAAaavv" 统计每一个字符出现的次数
    1.获取每一个英文字母  A - z


BCZAav你好世界

"BBBCCCZZZZAAaavv你好你好世界世界" 统计每一个字符出现的次数

AAAABBBBCCCDDDD

 */
public class Test5 {
    @Test
    public void test03(){
        String s = "BBBCCCZZZZAAaavv你好你好世界世界";
        //1.对字符串进行排序
          //1.1将字符串转为字符数组
        char[] chars = s.toCharArray();
        //1.2 对字符数组排序
        Arrays.sort(chars);
        //2.将字符数组转为字符串
        String s1 = new String(chars);//

        System.out.println("s1 = " + s1);//AABBBCCCZZZZaavv世世你你好好界界
        //获取指定元素最后出现的下标 让最后的下标-开始下标+1
        //统计完一个元素后 要改变下标

        //3.核心
        for (int i = 0; i < s1.length(); ) {
            //获取元素
            char c = s1.charAt(i);
            //获取元素最后出现的下标
            int lastIndex = s1.lastIndexOf(c);
            System.out.println(c+"出现了 "+(lastIndex-i+1));
            i = lastIndex+1;
        }
    }

    @Test
    public void test02(){
        String s = "BBBCCCZZZZAAaavv你好你好世界世界";
        //1.获取字符串内不重复字符
         //创建一个字符串用于存储 不重复的字符
        String newStr = "";

        for (int i = 0; i < s.length(); i++) {
            //获取每一个字符
            char c = s.charAt(i);
            //如果 c 没有在 newStr 内出现过 添加到 newStr 否则不添加
            // 实现 字符串去重
            boolean contains = newStr.contains(c + "");
            if(!contains){
                newStr+=c;
            }
        }

       // System.out.println("newStr = " + newStr);//BCZAav你好世界

        //遍历不重复的字符串
        for (int i = 0; i < newStr.length(); i++) {
            char n = newStr.charAt(i);
            //定义变量 统计次数
            int count = 0;
            //遍历所有的内容
            for (int j = 0; j <s.length(); j++) {
                char m = s.charAt(j);
                if(n==m){
                    count++;
                }
            }
            System.out.println(n+" 出现了  "+ count);
        }



    }

    @Test
    public void test01(){
        String s = "BBBCCCZZZZAAaavv";
        //1.获取所有的字母
        for(char i = 'A';i<='z';i++){
            //统计每一个字母出现的次数
           int count = 0;
           //获取字符串内的每一个字符
            for (int j = 0; j < s.length(); j++) {
                char temp = s.charAt(j);
                if(i==temp){
                    count++;
                }
            }
            if(count!=0){
                System.out.println(i+" ---> "+count+"次");
            }

        }







    }
}
