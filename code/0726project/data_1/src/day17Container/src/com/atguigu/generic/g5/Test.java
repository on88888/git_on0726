package day17Container.src.com.atguigu.generic.g5;
//?: 可以接受任何类型
public class Test {

    public static void main(String[] args) {

        Student<Double> s1 = new Student<>("张三", 99.6);

        Student<Integer> s2 = new Student<>("李四", 100);

        Student<String> s3 = new Student<>("王五", "优秀");
        Student<Object> s4 = new Student<>("王五", 'A');

      //  Student<Object> []sArr = {s1,s2,s3,s4};
        Student<?> []sArr = {s1,s2,s3,s4};

    }
}
