package day07OOP.src.com.atguigu.static1;
/*
静态成员变量vs非静态成员变量

相同：
    1.代码位置相同  类中方法外
    2.都具有默认值
        byte short int long  0
        float double 0.0
        boolean false  char \u0000
        String null
不同：
    1.内存位置不同
            静态成员变量  方法区内
            非静态成员变量 堆中
    2.调用方式不同
            静态成员变量 类名.属性名  对象名.属性名
            非静态成员变量 对象名.属性名
    3.在内存中的分数不同
            静态成员变量 只会开辟一块空间  所有的对象共享这一份内存
            非静态成员变量 new 一次就会开辟一块新内存

    4.创建时间不同(在内存中开辟空间的时间)
            静态成员变量  在类加载时开辟空间
                          随着类的加载而加载
            非静态成员变量 创建对象时

   5.销毁时间
            静态成员变量  随着类的卸载而卸载
            非静态成员变量  当没有引用指向时 会被垃圾回收器回收

 */
public class StudentTest2 {
    public static void main(String[] args) {
        System.out.println("Student.country = " + Student.country);
        Student.country="中国";
        Student s1 = new Student();
        s1.name="李白";
        s1.age=19;
        s1.score=99;
        System.out.println("name = "+s1.name+",age = "+s1.age+", score= "+ s1.score+", country = "+s1.country);
        Student.country="China";
        Student s2 = new Student();
        s2.name="杜甫";
        s2.age=29;
        s2.score=97;
        System.out.println("name = "+s2.name+",age = "+s2.age+", score= "+ s2.score+", country = "+Student.country);
    }
}
