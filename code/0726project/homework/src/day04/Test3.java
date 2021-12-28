package day04;

public class Test3 {
    public static void main(String [] args){
        java.util.Scanner input = new java.util.Scanner(System.in);
        int numz = 0, numf = 0;
        int sum;
        do {
            System.out.println("请输入整数：");
            sum = input.nextInt();
            if (sum > 0) {
                numz += 1;
            }else if(sum<0){
                numf += 1;
            }
        }while(sum!=0);//适合输入后判断循环

        System.out.println("numz: "+numz+", numf: "+numf);
    }
}
