package day04;

public class Test4 {
    public static void main(String [] args){
        java.util.Scanner input = new java.util.Scanner(System.in);
        int rd = (int)(Math.random()*100);
        int num;
        //boolean flag = false;
        int count = 0;
        do {
            System.out.println("请输入一个100以内的正整数：");
            num = input.nextInt();
            count++;
            if (num == rd) {
                System.out.println("猜对了！");
                //flag = true;
            } else if (num > rd) {
                System.out.println("大了！");
            } else {
                System.out.println("小了！");
            }
        }while(num!=rd);
        //}while(flag!=true);
        System.out.println("一共猜了"+count+"次");

    }
}
