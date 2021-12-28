package day09OOP.src.com.atguigu.rescursion.r2;

public class Steps {

    public static void main(String[] args) {

        System.out.println("steps(3) = " + steps(3));
        System.out.println("steps(4) = " + steps(4));


    }
    public static int steps(int num){

        if(num<=2){
            return num;
        }
      /*  if(num==1){
            return 1;
        }
        if(num==2){
            return 2;
        }*/

        return steps(num-1)+steps(num-2);
    }
}
