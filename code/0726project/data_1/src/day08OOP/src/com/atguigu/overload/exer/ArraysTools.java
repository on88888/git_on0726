package day08OOP.src.com.atguigu.overload.exer;

public class ArraysTools {
    // char[]数组遍历
    public static String getInfo(char[] arr){
        //定义字符串 记录结果
        String str = "[";
        for (int i = 0; i < arr.length; i++) {
            //如果是最后一个元素 ]
            if(i==arr.length-1){
                str+=arr[i]+" ]";
            }else{
                str+=arr[i]+", ";
            }
            //如果不是最后一个元素 ,
        }
        return str;
    }

    // int[]数组遍历
    public static String getInfo(int[] arr){
        String  str = "[";

        for (int i = 0; i < arr.length; i++) {

            //如果是最后一个元素 ]
            if(i==arr.length-1){
                str = str+ arr[i] +" ]";
            }else{
                //如果不是最后一个元素 ,
                str = str+arr[i]+", ";
            }
        }

        return str;
    }


    public static void sort(char[] arr){

        for (int i = 0; i < arr.length-1; i++) {

            for (int j = 0; j < arr.length-i-1; j++) {
                if(arr[j]>arr[j+1]){
                    char temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }

    public static void sort(int [] arr){
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;

            for(int j = i+1;j<arr.length;j++){

                if(arr[j]<arr[minIndex]){
                    minIndex = j;
                }
            }
            if(i!=minIndex){
                int temp = arr[i];
                arr[i]=arr[minIndex];
                arr[minIndex]=temp;
            }
        }
    }
}
