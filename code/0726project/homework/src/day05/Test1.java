package day05;

public class Test1 {
    public static void main(String[] args) {
        int [] a =new int[10];
        System.out.println("您的大乐透号码为：");
        for(int i=0;i<10; i++){
            a[i] = (int)(Math.random()*99+1);
            System.out.print(a[i]+" ");
        }
    }

}
