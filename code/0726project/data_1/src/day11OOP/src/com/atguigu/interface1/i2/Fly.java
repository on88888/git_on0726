package day11OOP.src.com.atguigu.interface1.i2;
/*
接口的属性是全局静态常量: 默认被  public static final 修饰
接口内的抽象方法 默认被 public abstract 修饰
接口内可以有静态的方法  默认被 public 修饰
接口内有默认方法  哪一个实现类需要重写 直接重写即可 不会对其他类造成影响
                默认被 public 修饰
 */
public interface Fly {
  public static final   int NUM =10;
  public abstract void a();
 // void b();

   static void show(){
      System.out.println("this is show()");
  }

  public default void eat(){

      System.out.println("吃东西");
  }
}
class Bird implements Fly{
    @Override
    public void a() {
        System.out.println("this is bird a()");
    }

    @Override
    public void eat() {
        System.out.println("小鸟吃虫子 ....");
    }
}
class Superman implements Fly{
    @Override
    public void a() {
        System.out.println("this is superman a()");
    }
}