package day04;

public class Test1 {
    public static void main(String [] args){
       int sum = 0;

        for(int i=1;i<=100;i++) {
             sum +=i;
        }

//        int i = 1;
//        while(i!=101){
//            sum +=i;
//            i++;
//        }
//
//        int i = 1;
//        do{
//            sum +=i;
//            i++;
//        }while(i<=100);//while(i!=101);
        System.out.println("sum ="+sum);
    }

}
