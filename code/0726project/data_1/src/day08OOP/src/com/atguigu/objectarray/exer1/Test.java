package day08OOP.src.com.atguigu.objectarray.exer1;

public class Test {
    public static void main(String[] args) {

        Student s1 = new Student();
        s1.name="张三";
        s1.score=90;

        Student s2 = new Student();
        s2.name="李四";
        s2.score=80;

        Student s3 = new Student();
        s3.name="王五";
        s3.score=96;
        Student s4 = new Student();
        s4.name="赵六";
        s4.score=89;

        Student[] ss = {s1,s2,s3,s4};

        //排序前
        showInfo(ss, "排序前 = ");

        //排序
        sort(ss);

        System.out.println("------------------------\n\n");
        showInfo(ss, "排序后 = ");
    }

    public static void sort(Student[] ss) {
        for (int i = 0; i < ss.length-1; i++) {

            for (int j = 0; j < ss.length - i - 1; j++) {
                //前一个对象的分数>后一个对象的分数
                if(ss[j].score>ss[j+1].score){
                    //两个对象位置交换
                    Student temp =ss[j];
                    ss[j]=ss[j+1];
                    ss[j+1]=temp;

                }

            }

        }
    }

    public static void showInfo(Student[] ss, String s5) {
        for (Student s : ss) {
            System.out.println(s5 + s.getInfo());
        }
    }
}
