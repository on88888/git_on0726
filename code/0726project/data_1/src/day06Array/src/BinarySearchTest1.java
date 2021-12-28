package day06Array.src;

public class BinarySearchTest1 {

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50, 60, 70};

        //定义变量 记录要查找元素的下标
        int index = -1;
        //定义要查找的元素
        int key = 90;

        //开始下标
        int statrIndex = 0;
        //结束下标
        int endIndex = arr.length - 1;
        //中间下标
        int midIndex = (statrIndex + endIndex) / 2;

        //开始下标<=结束下标
        while (statrIndex <= endIndex) {
            //中间下标元素
            int value = arr[midIndex];
            //要查找元素>中间下标元素
            if (key > value) {
                // 开始下标改变 =中间下标+1
                statrIndex = midIndex+1;
            }else if(key<value){  //要查找元素<中间下标元素
                // 结束下标改变 =中间下标-1
                endIndex = midIndex-1;
            }else{
                //要查找元素==中间下标元素
                //找到了 记录下标  结束循环
                index=midIndex;
                break;
            }

            //只要开始下标或者中间下标发生改变 中间下标 要对应变化
                midIndex=(statrIndex+endIndex)/2;

        }

        if(index==-1){

            System.out.println("查无此元素 "+ key);
        }else{
            System.out.println(key+" 的下标是 "+ index);

        }
    }
}
