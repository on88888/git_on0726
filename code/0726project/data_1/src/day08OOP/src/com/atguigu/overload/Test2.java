package day08OOP.src.com.atguigu.overload;
/*
方法重载的要求：
    两同一不同
    同一类中 同一方法名  不同的形参列表
                                    类型
                                    数量
                                    顺序



 */
public class Test2 {
    public static void main(String[] args) {

        sum(10, 20);

        sum(10, 3.14);
    }
    public static void sum(int i1,int i2,int i3){

    }

    public static void sum(int i,int i1){

        System.out.println("int + int ");

    }
    public static void sum(int i,double v){

        System.out.println("int + double ");
    }
    public static void sum(double d1,int i1){
        System.out.println("double + int");
    }

    public static void sum(double i,double v){

        System.out.println("double + double ");

    }
}
