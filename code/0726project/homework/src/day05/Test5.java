package day05;

public class Test5 {
    public static void main(String[] args) {
        int[] num = {95,92,75,56,98,71,80,58,91,91};
        int sum = 0,svm,count = 0;
        for (int j : num) {
            sum += j;
        }
        svm = sum/num.length;
        for (int j : num) {
            if (j > svm) {
                count++;
            }  //不要使用break，会直接停止循环。用continue可以跳过不符合的条件

        }
        System.out.println("高于平均分"+svm+"的 个数有"+count+"个");
    }
}
