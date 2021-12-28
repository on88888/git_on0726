package day20Thread.single;

import org.junit.Test;

/*

单例：包证程序执行过程中只会有一个实例

饿汉式
    无论是否需要 都先把对象创建出来
懒汉式
    当真正需要实例(对象) 才进行创建
 */
public class HungryTest {


   @Test
    public void test01(){
/*

       Hungry h1 = new Hungry();
       Hungry h2 = new Hungry();
*/
      Hungry h1 = Hungry.INSTANCE;
      Hungry h2 = Hungry.INSTANCE;
      System.out.println(h1==h2);

   }
   @Test
   public void test02(){

      Hungry2 h1 = Hungry2.INSTANCE;
      Hungry2 h2 = Hungry2.INSTANCE;

      System.out.println(h1==h2);


   }

}
class Hungry{
   public static final Hungry INSTANCE = new Hungry();
   private Hungry(){}

}
enum Hungry2{
   INSTANCE
}
