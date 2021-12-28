package day08OOP.src.com.atguigu.method.exer1;

public class MathTools {

    public static String getYueShu(int num){
        //记录所有的约数
        String result = "";

        for(int i = 1;i<=num;i++){

            if(num%i==0){
             //   System.out.println(i);
                result += i+" ";

            }

        }
        return result;


    }


    //要判断的数
    public static void isZhiShu(int num){
        //假设 num 是质数
        boolean flag = true;

        //找 1和本身之外的约数范围
        for (int i = 2; i <num ; i++) {

            if(num%i==0){
                flag=false;
            }
        }
        if(flag){
            System.out.println(  num+" 是质数 ");
        }else{
            System.out.println(  num+" 不是质数 ");
        }
    }

}
