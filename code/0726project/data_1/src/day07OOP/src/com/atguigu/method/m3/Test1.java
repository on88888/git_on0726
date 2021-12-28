package day07OOP.src.com.atguigu.method.m3;
//定义一个方法 判断 数字是否为奇数  是 返回true 不是返回false
public class Test1 {
    public static void main(String[] args) {
        //接收的是方法执行的结果
        boolean b = isOdd(9);

        System.out.println("b = " + b);

        System.out.println("isOdd(10) = " + isOdd(10));


    }

    /**
     * 判断数字是否为奇数
     * @param num 要判断的数
     * @return true/fasle
     */
    public static boolean isOdd(int num){

       /* if(num%2!=0){
            return true;
        }else{
            return false;
        }*/

     //  return num%2!=0?true:false;
       return num%2!=0;
    }
}
