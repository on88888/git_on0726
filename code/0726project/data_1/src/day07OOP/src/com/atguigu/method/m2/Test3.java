package day07OOP.src.com.atguigu.method.m2;
/*
二.定义一个方法 接收一个整数 判断该数是否为质数


 */
public class Test3 {
    public static void main(String[] args) {

        isZhiShu(12);

        int m = 89;
        isZhiShu(m);



    }

    public static void isZhiShu(int num){

        //假设此数是质数
        boolean flag = true;
        //除了1和本身之外的约数范围
        for(int i=2;i<num;i++){

            if(num%i==0){
               flag=false;
               break;
            }
        }
        if(flag){
            System.out.println("是质数");
        }else{
            System.out.println("不是质数");
        }


    }
}
