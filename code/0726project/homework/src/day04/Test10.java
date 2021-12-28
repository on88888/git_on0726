package day04;

public class Test10 {
    public static void main(String []args){
        for(int i =1;i<=9;i++){
            for(int j=1;j<=i;j++){
                System.out.print(j+"*"+i+"="+(i*j) + "\t");
            }
            System.out.println();
        }
    }
}

//package Exercise;
//
//public class Test10 {
//    public static void main(String[] args) {
//        for(int i=1; i<=9; i++){
//            for(int j=1; j<=9-i; j++){
//                System.out.print("\t");
//            }
//            for(int k=1; k<=i; k++){
//                System.out.print(i + "*" + k + "=" + i*k + "\t");
//            }
//            System.out.println();
//        }
//    }
//}