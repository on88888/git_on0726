package day04;

public class Test8 {
    public static void main(String []args){
        for(int i=1;i<=100;i++){
            if(i%3!=0&&i%5!=0&&i%7!=0){
                System.out.println(i);
            }
        }
        System.out.println("-----------------------");
		/*
		使用continue
		*/
        for(int i=1; i<=100; i++){
            //其中3、5、7的倍数不打印
            //如果是3,5,7的倍数就跳过打印语句
            if(i%3==0 || i%5==0 || i%7==0){
                continue;
            }
            System.out.println(i);
        }

    }
}
