package day09OOP.src.com.atguigu.review;

/*
    新建一个坐标类 Point 有两个属性 x ,y
    新建一个测试类 在测试类的main()中 新建 Point 对象 并对属性 x,y赋值
    在测试类中 新建一个方法 swap() 让 Point作为形参  在方法内 交换 x y的 值
    输出  方法调用中 x= ,y=
    然后main() 输出
     方法调用前 x= ,y=
     调用方法
     方法调用后 x= ,y=
    画图 得出结论
 */
public class Test {
    public static void main(String[] args) {
        Point p = new Point();
        p.x=10;
        p.y=20;
        System.out.println("方法调用前  x = "+ p.x+", y = "+p.y);
        swap(p);
        System.out.println("方法调用后  x = "+ p.x+", y = "+p.y);
    }

    public static void swap(Point p){
        int temp = p.x;
        p.x = p.y;

        p = new Point();

        p.y=temp;
        System.out.println("方法调用中  x = "+ p.x+", y = "+p.y);
    }

}
