package day17Container.src.com.atguigu.generic.g5;

/*

获取学生数组内成绩最高的学生

? extends 类型 ： 指明上限
        最高是 [类型]

? super 类型： 指明下限

 */
public class Test2 {


    public static void main(String[] args) {

        Student<Integer> s1 = new Student<>("张三", 90);
        Student<Integer> s2 = new Student<>("李四", 70);
        Student<Integer> s3 = new Student<>("王五", 100);
        Student<Integer> s4 = new Student<>("赵六", 80);

        Student<Integer>[] sArr = new Student[]{s1, s2, s3, s4};
        Student<? extends Comparable> maxScore = getMaxScore(sArr);

        System.out.println("maxScore = " + maxScore);

        Student<Animal> [] s = new Student[]{};


       // getMaxScore(s);Animal 没有实现Comparable 接口

    }
    //获取学生数组内成绩最高的学生
    public static Student<? extends Comparable> getMaxScore(Student<? extends Comparable>[] arr){
        Student max = arr[0];

        for (Student<? extends Comparable> s : arr) {

            if(s.score.compareTo(max.score)>0){

                max= s;
            }

        }

        return max;


    }


}
