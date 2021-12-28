package day11OOP.src.com.atguigu.anno;



import java.util.Date;

/*
@Override 校验当前方法是否为重写的方法

@Deprecated 标记过时
                   过时的资源 不推荐使用 已经有了更好的方式替代
@SuppressWarnings 抑制警告
      @SuppressWarnings("all") 抑制所有的警告
 */
public class Test {
    @SuppressWarnings("all")
    public static void main(String[] args) {

        int m =10;

        Date date = new Date();
        date.getYear();
        Person person = new Person();
    }
}
