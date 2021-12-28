package day04;

public class Test2 {
    public static void main(String [] args) {
        int sum = 0;

        for (int i = 2; i <= 100; i+=2) {
            sum += i;
        }
        System.out.println("sum ="+sum);
    }
}

// l: for (int i = 1; i <= 4; i+=1) {
//           if(i==3){
//              continue l;
//           }//此if循环表示跳过i=3时的累加
//           sum += i;
//           }
// System.out.println("sum ="+sum);//该循环sum的结果为1+2+4