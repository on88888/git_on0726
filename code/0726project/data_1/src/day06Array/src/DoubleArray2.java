package day06Array.src;

/*
二维数组的初始化：
静态初始化
    数据类型[][] 数组名 = {{值...},{值...}};声明和赋值不可以分开

     数据类型[][] 数组名 = new 数据类型[][]{{值...},{值...}};  声明和赋值可以分开

动态初始化

注意：
    1.二维数组的长度数组内一维数组的个数
    2.数组名[下标1][下标2]
        下标1： 一维数组的下标
        下标2：一维数组内元素的下标

 */
public class DoubleArray2 {
    public static void main(String[] args) {
        String[][]sArr;
        sArr = new String[][]{{"李白","杜甫"},{"王安石"},{"李商隐","白居易"}};

        System.out.println("sArr.length = " + sArr.length);
        System.out.println("sArr[2][1] = " + sArr[2][1]);


        System.out.println("------------------------------");

        int[]arr = {10,20,30};
        System.out.println("arr = " + arr);//[I@4554617c
        System.out.println("arr[0] = " + arr[0]);
        System.out.println("arr.length = " + arr.length);//3

        int[][] arr1= {{10,20},{30,40}};

        System.out.println("arr1 = " + arr1);//[[I@74a14482
        System.out.println("arr1.length = " + arr1.length);
        System.out.println("arr1[0] = " + arr1[0]);// [I@4554617c
        //获取arr1数组内第一个一维数组中的第一个元素

        System.out.println("arr1[0][0] = " + arr1[0][0]);
        //获取arr1数组内第二个一维数组中的第二个元素
        System.out.println("arr1[1][1] = " + arr1[1][1]);


    }
}
