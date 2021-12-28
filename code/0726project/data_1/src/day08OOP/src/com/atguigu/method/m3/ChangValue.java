package day08OOP.src.com.atguigu.method.m3;
/*
基本数据类型传递的是值的副本
 */
public class ChangValue {
    public static void main(String[] args) {
        int n = 10;
        int m = 20;
        System.out.println("方法调用前  n = " + n+", m = "+m);
        swap(n, m);
        System.out.println("方法调用后  n = " + n+", m = "+m);
    }
    public static void swap(int n,int m){
        int temp = n;
        n = m;
        m = temp;
        System.out.println("方法调用中  n = " + n+", m = "+m);
    }
}
