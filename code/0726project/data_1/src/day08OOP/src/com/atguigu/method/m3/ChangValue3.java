package day08OOP.src.com.atguigu.method.m3;

public class ChangValue3 {
        public static void main(String[] args) {
            String[] sArr = {"李白","杜甫","王安石"};
            System.out.println("方法调用前 sArr[0] "+ sArr[0]);//李白
            swap(sArr);
            System.out.println("方法调用后 sArr[0] "+ sArr[0]);//李白
        }

        public static void swap(String [] sArr){

            sArr= new String[]{"范冰冰","李冰冰"};

            sArr[0]="苏轼";

            System.out.println("方法调用中 sArr[0] "+ sArr[0]);//苏轼

    }
}
