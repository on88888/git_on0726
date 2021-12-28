package day05;

import java.util.Arrays;

public class Test9 {
    public static void main(String[] args) {
        java.util.Scanner input = new java.util.Scanner(System.in);
        System.out.println("请输入学员人数：");
        int a = input.nextInt();
        int index = 0;
        int[] student = new int [a];
        do{
            System.out.println("请输入各学员分数：");
            int result = input.nextInt();
            student[index] = result;
            index++;
        }while(index<a);
        Arrays.sort(student);//将数组中的元素进行从大到小的排序
        System.out.println("Arrays.toString(student) = " + Arrays.toString(student));
    }
}
