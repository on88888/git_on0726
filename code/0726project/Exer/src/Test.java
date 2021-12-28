import java.util.Scanner;

/**
 * @Author 0726
 * @ClassName Test.java
 * @createTime 2021年10月09日 21:09
 */
public class Test {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("请输入参数：");
        int a = input.nextInt();

        int sum = 0;
        for (int i = 1; i <= a; i++) {
            System.out.println("Fibonacci("+i+") = " + Fibonacci(i));
            sum += Fibonacci(i);
        }

        System.out.println("sum = " + sum);

    }

    private static int Fibonacci(int n){
        if (n<=2){
            return 1;
        }
        return Fibonacci(n-1)+Fibonacci(n-2);
    }
}


