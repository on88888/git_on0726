package day09OOP.src.com.atguigu.encap.e1;
/*
不同的修饰符 修饰资源 访问权限
private  只能在本类中可见
default 本包下可见
protected 本包下可见 不同包下的子类可见
public 任意位置不见
 */
public class Test {
    public static void main(String[] args) {

        Studnet s1 = new Studnet();
        System.out.println("s1.name = " + s1.name);
        System.out.println("s1.score = " + s1.score);
        System.out.println("s1.age = " + s1.age);
    }
}
