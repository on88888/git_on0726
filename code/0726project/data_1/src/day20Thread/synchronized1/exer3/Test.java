package day20Thread.synchronized1.exer3;

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


使用静态同步方法 完成  取钱操作

静态同步方法 的同步监视器对象 是 Account.class
 */
public class Test {

    public static void main(String[] args) {


        ZsThread zsThread = new ZsThread();
        zsThread.setName("张三");



        ZsWifeThread zsWifeThread = new ZsWifeThread();
        zsWifeThread.setName("翠花");

        zsThread.start();
        zsWifeThread.start();



    }
}
