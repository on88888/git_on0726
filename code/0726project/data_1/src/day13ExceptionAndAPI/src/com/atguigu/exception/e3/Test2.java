package day13ExceptionAndAPI.src.com.atguigu.exception.e3;
/*

try{
}catch(){
}finally{
}
finally:无论如何都要执行的代码 放到 finally
强制退出 jvm System.exit(0);
 */
public class Test2 {
    public static void main(String[] args) {
        System.out.println(1111);
        try{
            int[] arr = {10,20};
            int m = 9/2;
            System.out.println("arr[1] = " + arr[1]);
            System.out.println(222);

            System.out.println(333);
            //return;
            System.exit(0);
        }catch (ArrayIndexOutOfBoundsException a){
            System.out.println("a.getMessage() = " + a.getMessage());
        }finally {
            System.out.println("Game Over");
        }




    }
}
