package day05Array.src;

/*
求最小值及其下标
  假设最小值

  让数组元素与最小值进行比较 如果比最小值还小 说明数组元素是最小值


 */
public class ArryayTest6 {

    public static void main(String[] args) {
        int [] arr = {1,9,-1,900,103,89};

        //假设最小值
        int minNum = arr[1];
        //最小值下标
        int minIndex = 1;

        for (int i = 0; i < arr.length; i++) {
           // 让数组元素与最小值进行比较 如果比最小值还小 说明数组元素是最小值
            if(arr[i]<minNum){
                minNum = arr[i];
                //记录最小值下标
                minIndex=i;

            }

        }

        System.out.println("minNum = " + minNum);
        System.out.println("minIndex = " + minIndex);


    }
}
