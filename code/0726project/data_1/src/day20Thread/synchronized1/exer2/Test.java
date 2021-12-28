package day20Thread.synchronized1.exer2;



/*

Account
    余额 500元

    取钱方法

    获取余额的方法

 继承Thread类的方式
    创建两个线程 张三线程 ZsThread extend Thread
            取钱  400元
             张三线程 取钱成功  余额 是 100元
             张三线程 取钱失败  余额 是 100元
    创建两个线程 张三媳妇儿线程 ZsWifeThread extends Thread
             取钱  400元
               张三媳妇儿线程 取钱成功  余额 是 100元
               张三媳妇儿线程 取钱失败  余额 是 100元


同步普通方法解决  同步监视器对象是 this
 */
public class Test {

    public static void main(String[] args) {

        //在两个线程共同出现的位置 新建一个账户
        Account account = new Account();

        ZsThread zsThread = new ZsThread();
        zsThread.setName("张三");
        zsThread.setAccount(account);


        ZsWifeThread zsWifeThread = new ZsWifeThread(account);
        zsWifeThread.setName("翠花");

        zsThread.start();
        zsWifeThread.start();



    }
}
