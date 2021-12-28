package day06Array.src;

public class DoubleArray5 {
    public static void main(String[] args) {
        String[][]   sArr = new String[][]{{"李白","杜甫"},{"王安石","苏轼"},{"李商隐","白居易","李清照"}};

        //i:代表二维数组内一维数组的下标
        for (int i = 0; i < sArr.length; i++) {//0 1 2
            //j：一维数组内元素下标
            for(int j = 0;j<sArr[i].length;j++){
                System.out.print(sArr[i][j]+"\t");

            }
            System.out.println();
        }

    }
}
