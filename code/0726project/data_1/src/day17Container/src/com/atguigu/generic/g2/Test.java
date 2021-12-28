package day17Container.src.com.atguigu.generic.g2;
/*

  我们要声明一个学生类，该学生包含姓名、成绩，而此时学生的成绩类型不确定，为什么呢，
  因为，
  语文老师希望成绩是“优秀”、“良好”、“及格”、“不及格”，
  数学老师希望成绩是89.5, 65.0，
  英语老师希望成绩是'A','B','C','D','E'。那么我们在设计这个学生类时，就可以使用泛型。
 */
public class Test {

    public static void main(String[] args) {

        Student<String> s1 = new Student<>("张三", "优秀");


        Student<Character> s2 = new Student<>("李四", 'A');


        Student<Double> s3 = new Student<>("王五", 100.0);


    }
}

