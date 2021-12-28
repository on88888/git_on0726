package day06Array.src;

import java.util.Arrays;

public class DeleteTest {
    public static void main(String[] args) {
        String [] sArr = {"张三","李四","王五","赵六","王二麻子",null};
                        //"张三","王五","赵六","王二麻子",王二麻子,null}
        System.out.println("Arrays.toString(sArr) = " + Arrays.toString(sArr));
        //0.找到删除元素的下标  李四  1
        int deleteIndex = 1;
        //1.将数据向前移动
        System.arraycopy(sArr, deleteIndex+1, sArr, deleteIndex, 3);
        //2.要将后面的最后一个有价值信息设置为null
        sArr[4]=null;
        System.out.println("Arrays.toString(sArr) = " + Arrays.toString(sArr));


    }
}
