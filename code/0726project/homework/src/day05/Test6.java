package day05;

public class Test6 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,3,2,1};
        int[] arr1 = {1,2,3,4,5,2,1};
        boolean flag = true;
        boolean flag1 = true;
        for (int i = 0; i < arr.length-1-i; i++) {
            if(arr[i]!=arr[arr.length-1-i]){
                flag = false;
                //break;
                continue;
            }
            if(arr1[i]!=arr1[arr1.length-1-i]){
                flag1 = false;
                //break;
            }
        }
        System.out.println(java.util.Arrays.toString(arr)+" 是否对称："+flag);
        System.out.println(java.util.Arrays.toString(arr1)+" 是否对称："+flag1);
    }
}
