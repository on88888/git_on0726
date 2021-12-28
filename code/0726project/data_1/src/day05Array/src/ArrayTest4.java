package day05Array.src;

/*
1. 查找指定元素的下标
        有输出下标
        没有输出查无此元素
 */
public class ArrayTest4 {
    public static void main(String[] args) {

        int[] arr = {10,20,30,40,50,60,10};

        //创建键盘输入对象
        java.util.Scanner in = new java.util.Scanner(System.in);

        //给出提示语句
        System.out.println("请您 输入数字");

        //接收数据
        int num = in.nextInt();
        //对数字进行判断

        //定义一个变量 记录下标
        int index = -1;

        for (int i = 0; i < arr.length; i++) {
            if(num==arr[i]){
                //System.out.println(num+"出现了 下标为："+i);
                index= i;
                //加 break 让index 记录 第一次出现的下标
                break;
            }
        }

        //输出结果
        if(index==-1){
            System.out.println("没有此元素 "+ num);
        }else{
            System.out.println(num+"出现了 下标为："+index);
        }

    }
}
