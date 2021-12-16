import java.util.ArrayList;
import java.util.List;

public class AA {
    public static void main(String[] args) {

        List<A> list1 = new ArrayList<A>();
        list1.add(new A());
        list1.add(new A());
        list1.add(new A());

        List<A1> list2 = new ArrayList<A1>();
        list2.add(new A1());
        list2.add(new A1());
        list2.add(new A1());
        //非变
        //list1 = list2


    }
}

class A{
    public String name = "zhangsan";
    public int age = 20;

    public int m1(int x){
        return x+x;
    }
}

class A1 extends A{
    public String name = "lisi";
    public int age = 30;

    public int m1(int x){
        return x*x;
    }
}
