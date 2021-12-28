package day09OOP.src.com.atguigu.inheritance.i4;
/*
为什么进行方法重写？
    当父类的方法 不能满足子类的需求 进行方法重写
子类重写父类方法时的要求？
    权限修饰符 返回值类型 方法名(形参列表){
    }
    1.权限修饰符 不能比父类更加严格（>=父类的访问权限）
    2.返回值类型
        2.1基本数据类型 必须与父类一致
        2.2引用数据类型 那么可以是父类也可以是父类的子类
    3.方法名必须与父类一致
    4.形参类比必须与父类一致

注意：
    静态方法没有重写 是属于类的
 */
public class Test {
    public static void main(String[] args) {
        Student s1 = new Student();

        s1.name = "李白";
        s1.score=99;
        s1.show();

        Student.show1();
    }
}
