package day05Array.src;

/*
2.求最值
     2.1假设一个最大值

     2.2 让数组元素与最大值进行比较  如果比最大值还大 说明数组元素是最大值

     注意： 设置最大值时 要使用数组内的元素
 */
public class ArrayTest5 {
    public static void main(String[] args) {

        int [] arr = {1,9,-1,900,103,89};

        //假设最大值
        int maxNum = arr[0];

        for (int ele : arr) {
// 让数组元素与最大值进行比较  如果比最大值还大 说明数组元素是最大值
            if(ele>maxNum){
                maxNum=ele;
            }
        }
        System.out.println("maxNum = " + maxNum);

    }
}
