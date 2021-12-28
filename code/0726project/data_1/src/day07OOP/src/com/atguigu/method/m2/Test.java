package day07OOP.src.com.atguigu.method.m2;

/*
形参:
    方法的声明处 数据类型 变量名

    规定方法调用处传递的实参的类型  个数 顺序

实参:
    既可以是常量值
    也可以是变量
    实参的值与形参的类型匹配即可

定义一个方法 求任意两个数的和?

 */
public class Test {

    public static void isOuShu(int num){

        if(num%2==0){
            System.out.println(num+"是偶数");
        }else{
            System.out.println(num+"不是偶数");
        }

    }
    public static void main(String[] args) {
        isOuShu(10);
        int j = 90;
        isOuShu(j);
       /* sum(3,9);
        sum(15, 78);
        sum(10, 3.14);
       // sum(a:8,b:7);
        int n = 10;
        double m = 4.56;
        sum(n, m);
*/

    }
    public static void sum(int a,double b){
        System.out.println(a+b);
    }

}
