/**
 * @Author 0726
 * @ClassName Test
 * @createTime 2021年10月22日 18:15
 */
public class Test {
    /**
     * java中  static修饰的 可以直接 类名.方法名 引用 --Person.hello()
     * java中  非static修饰的 需要创建对象调用
     * @param args
     */
    public static void main(String[] args) {

        //静态方法
        B.m1();

        //非静态方法
        B b = new B();
        b.m2();
    }
}
class B{
    //静态方法
    static void m1(){
        System.out.println("ssssss");
    }

    //非静态方法
    void m2(){
        System.out.println("ffffff");
    }
}