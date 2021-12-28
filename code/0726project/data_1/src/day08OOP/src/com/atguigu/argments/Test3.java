package day08OOP.src.com.atguigu.argments;
/*

 分割符   多个字符串
  -       hello world  good
            hello-world-good
  *       1 2 3 4
            1*2*3*4

 */
public class Test3 {
    public static void main(String[] args) {


        String concat = concat('-', "H", "K", "L", "Z");
        System.out.println("concat = " + concat);

        System.out.println("concat('*', \"1\",\"2\",\"3\",\"4\") = " + concat('*', "1", "2", "3", "4"));

    }

    public static String concat(char seq,String...s){
        //定义字符串接收数据
        String result = "";

        for (int i = 0; i < s.length; i++) {
            //判断是否为最后元素
            if(i==s.length-1){
                result+=s[i];
            }else{
                //不是最后一个元素有分割符
                result+=s[i]+seq;
            }
        }





        return result;



    }
}
