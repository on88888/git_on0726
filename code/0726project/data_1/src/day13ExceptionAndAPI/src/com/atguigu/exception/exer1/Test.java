package day13ExceptionAndAPI.src.com.atguigu.exception.exer1;

public class Test {
    public static void main(String[] args) {


        Account account = new Account("001", 10000);
        System.out.println(account);
        try {
            account.withDraw(2000);

            account.saveMoney(-5000);
        }catch (Exception e){
            System.out.println("发生了异常");
        }
        System.out.println(account);
    }
}
