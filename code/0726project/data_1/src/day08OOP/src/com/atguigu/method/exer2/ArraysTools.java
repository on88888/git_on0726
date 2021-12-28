package day08OOP.src.com.atguigu.method.exer2;

public class ArraysTools {


    // [10, 20, 30, 40, 90, 100]
    public static String toString(int[] arr){

        //定义字符串接收所有的数据
        String result ="[";


        for (int i = 0; i < arr.length; i++) {

            //判断是否为最后一个元素 最后一个元素后面是 ]
            if(i==arr.length-1){
                result = result+arr[i]+" ]";
            }else{
                result = result+arr[i]+" ,";
            }
        }






        return result;
    }


}
