package day04;

public class Test9 {
    public static void main(String []args) {
        for (int i = 1; i <= 1000; i++) {
            int sum = 0;
            for (int j = 1; j < i; j++) {
                if (i % j == 0) {
                    sum += j;
                }
            }
            if(i == sum){
                System.out.println(i);
//                System.out.print(i+"=");
//                for(int j = 1; j < i; j++){
//                    if (i % j == 0) {
//                        int a = j;
//                        System.out.print(a+"+");
//                    }
//
//                }
//                System.out.println();
            }
        }
    }
}


//    public static void main(String[] args){
//        //找出1000以内的所有完数
//        for(int i=1; i<1000; i++){
//            int iSum = 0;
//            for(int j=1; j<i; j++){
//                //如果j能够把i整除了，j就是i的因子
//                if(i%j==0){
//                    iSum += j;
//                }
//            }
//            //（2）判断因子之和与i是否相等，如果相等就是完数
//            if(i == iSum){
//                System.out.println(i);
//            }
//        }
//    }
