package day04;

public class Test5 {
    public static void main(String [] args){
        int ge,shi,bai,sum,count=0;
        for(int i=100;i<1000;i++){
            ge = i%10;
            shi = (i/10)%10;
            bai = i/100;
            sum = ge*ge*ge + shi*shi*shi + bai*bai*bai;
            if(i==sum){
                System.out.println("水仙花数有："+i);
                count++;
            }
        }
        System.out.println("水仙花数有"+count+"个");
    }
}
