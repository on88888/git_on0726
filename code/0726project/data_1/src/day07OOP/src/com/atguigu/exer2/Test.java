package day07OOP.src.com.atguigu.exer2;

public class Test {
    public static void main(String[] args) {

        Account.rate=0.035;

        Account a1 = new Account();
        a1.no="001";
        a1.balance=1_0000_0000;

        System.out.println("账户是："+ a1.no+"，余额是："+a1.balance+"，利率是："+a1.rate);
        Account a2 = new Account();
        a2.no="002";
        a2.balance=2_000;
        System.out.println("账户是："+ a2.no+"，余额是："+a2.balance+"，利率是："+Account.rate);



    }
}
