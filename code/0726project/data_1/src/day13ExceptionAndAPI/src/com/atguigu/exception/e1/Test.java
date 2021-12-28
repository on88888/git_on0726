package day13ExceptionAndAPI.src.com.atguigu.exception.e1;
/*
异常的体系：
    Throwable
            getMessage();获取发生异常的原因
            printStackTrace();发生异常的原因

         -- Error: 程序员不需要处理的异常

         -- Exception ： 程序员处理的异常

            -- 运行时异常： 代码真正跑起来 才会出现的异常

            -- 编译时异常/检查异常/受检异常： 写完代码就报错
 */
public class Test {


    public static void main(String[] args) {


        System.out.println(1111);


        try {
            int[] arr = {10, 20, 30};

            System.out.println(arr[10]);
        }catch (ArrayIndexOutOfBoundsException a){

      //     a.printStackTrace();
            System.out.println("请您检查网络");
         //   System.out.println("a.getMessage() = " + a.getMessage());
        }

        System.out.println("Game Over");


    }

}
