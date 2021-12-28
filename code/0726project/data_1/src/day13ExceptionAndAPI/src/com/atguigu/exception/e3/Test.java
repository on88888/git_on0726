package day13ExceptionAndAPI.src.com.atguigu.exception.e3;
/*
try{
    可能发生异常的代码

}catch(异常类型 变量名){

}
注意：
    1.try中发生了异常 那么 异常下面的代码不会执行 直接进到 catch内
    2.try{} 是有作用域限制 想要在其他位置使用需要进行作用域提升
    3.只有发生了对应的异常类型 才可以进行捕获
    4.想要捕获多个异常
            4.1 使用 | 进行连接
                    catch(异常类型1 | 异常类型2 | .... 变量名)
            4.2多重catch

            4.3使用父类
                如果存在多重catch 一定按照 从小到大的顺序写


 */
public class Test {
    public static void main(String[] args) {

        System.out.println(11111);

        String [] sArr = {"杜甫","李白"};
        int m =0;
        try {
            System.out.println("sArr[1] = " + sArr[1]);
            m = 10;
            System.out.println(10/0);
            System.out.println(222);
            System.out.println(333);
        }catch (Exception a){
            a.printStackTrace();
            System.out.println("a.getMessage() = " + a.getMessage());
        }
        /*catch (ArrayIndexOutOfBoundsException a){
            a.printStackTrace();
            System.out.println("a.getMessage() = " + a.getMessage());
        }catch (ArithmeticException a){
            a.printStackTrace();
            System.out.println("a.getMessage() = " + a.getMessage());
        }catch (RuntimeException r){

        }*/


        /*catch (ArrayIndexOutOfBoundsException  | ArithmeticException a){
            System.out.println(m);
            a.printStackTrace();
            System.out.println("a.getMessage() = " + a.getMessage());
        }*/

        System.out.println("Game Over" + m);

    }
}
