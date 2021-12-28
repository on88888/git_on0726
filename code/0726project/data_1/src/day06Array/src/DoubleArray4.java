package day06Array.src;

public class DoubleArray4 {
    public static void main(String[] args) {
        String[][]   sArr = new String[][]{{"李白","杜甫"},{"王安石","苏轼"},{"李商隐","白居易","李清照"}};
        //遍历第一个一维数组
        for (int i = 0; i < sArr[0].length; i++) {
            System.out.print(sArr[0][i]+"\t");
        }
        System.out.println();
        //遍历第二个一维数组
        for (int i = 0; i < sArr[1].length; i++) {
            System.out.print(sArr[1][i]+"\t");
        }
        System.out.println();
        //遍历第三个一维数组
        for (int i = 0; i < sArr[2].length; i++) {
            System.out.print(sArr[2][i]+"\t");

        }
        System.out.println();

    }
}
