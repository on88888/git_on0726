package day13ExceptionAndAPI.src.com.atguigu.exception.exer1;

public class Account {
    private String id;
    private double balance;

    public Account(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public Account() {
    }
    //取钱
    public void withDraw(int money) throws IllegalArgumentException,UnsupportedOperationException{
        //1.取款金额为负数
        if (money < 0) {
            throw new IllegalArgumentException("取款金额有误，不能为负数");
        }
        //2.取款金额超余额
        if(money>balance){
            throw new UnsupportedOperationException("取款金额不足，不支持当前取款操作");
        }
        //3.正常取钱
        balance-=money;

    }
//存钱
    public void saveMoney(int money){

        if(money<0){
            throw new IllegalArgumentException("存款金额有误，不能为负数");
        }

        balance+=money;

    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", balance=" + balance +
                '}';
    }
}
