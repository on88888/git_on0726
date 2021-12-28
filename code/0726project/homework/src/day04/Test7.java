package day04;//package day04;
//
//public class Test7 {
//    public static void main(String []args){
//        java.util.Scanner input = new java.util.Scanner(System.in);
//        System.out.println("请输入第一个正整数：");
//        int m = input.nextInt();
//        System.out.println("请输入第二个正整数：");
//        int n = input.nextInt();
//        int max = m>n?m:n;
//        int min = m<n?m:n;
//        int maxyue = 0;
//        int minbei = 0;
//        for(int i=1;i<=min;i++) {
//            if (m % i == 0 && n % i == 0) {
//                maxyue = i;
//                break;
//            }
//        }
//        for (int j=max;j<=m*n;j++){
//            if(j%m==0&&j%n==0){
//                minbei = j;
//                break;
//            }
//        }
//
//        System.out.println(m+"和"+n+"的最大公约数为："+maxyue+"，最小公倍数为"+minbei);
//
//    }
//}

public class Test7 {
    public static void main(String[] args) {
        //输入两个正整数m和n
        java.util.Scanner input = new java.util.Scanner(System.in);
        int m;
        while (true) {
            System.out.print("请输入第一个正整数m的值：");
            m = input.nextInt();
            if (m <= 0) {
                System.out.println(m + "不是正整数，请重写输入！");
            } else {
                break;
            }
        }
        int n;
        while (true) {
            System.out.print("请输入第二个正整数n的值：");
            n = input.nextInt();
            if (n <= 0) {
                System.out.println(n + "不是正整数，请重写输入！");
            } else {
                break;
            }
        }

        System.out.println("两个正整数：" + m + "," + n);
        int max = m >= n ? m : n;
        int min = m < n ? m : n;
        //第二步：从小的开始查找
        //这里的1也可以修改为min的平方根
        int maxYue = 1;
        for (int i = min; i >= 1; i--) {
            //看i是否能够同时把m和n都整除
            if (m % i == 0 && n % i == 0) {
                maxYue = i;
                break;
            }
        }
        System.out.println(maxYue + "是" + m + "和" + n + "的最大公约数");
        //最小公倍数 = m*n / 最大公约数
        //System.out.println(m*n/maxYue +"是" + m + "和" + n + "的最小公倍数");
        //如果不知道这个公式
        //从max开始找，一直找到m*n
        for (int i = max; i <= m * n; i++) {
            if (i % m == 0 && i % n == 0) {
                System.out.println(i + "是" + m + "和" + n + "的最小公倍数");
                break;
            }
        }
    }
}
