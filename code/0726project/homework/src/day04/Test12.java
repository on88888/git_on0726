package day04;

public class Test12 {
    public static void main(String []args){
        for(int i = 2;i<=100;i++){
            //定义变量 假设当前数字是质数
            boolean flag = true;
            //确定每一个数的约数范围 10  [2,9] 97 [2,96]
            for(int j = 2;j<i;j++){
                //出现了1和本身之外能被整除的数
                if(i%j==0){
                    //假设失败
                    flag = false;
                    break;
                }
            }
            //当所有的约数查找完毕 输出最终结果
            if(flag){
                System.out.println(i);
            }
        }
    }
}
