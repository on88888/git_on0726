package day17Container.src.com.atguigu.generic.g3;

import org.junit.Test;
/*
T extends 类型：
         指明上限


 */
public class Test2 {

  @Test
  public void test01(){

      int[] arr = {10,20,30,40};

      //sort(arr);
      B[] bs = {new B(),new B()};

      sort(bs);

  }
    public static <T extends Comparable> void sort(T[] arr){

    }
}

class  B implements Comparable{


    @Override
    public int compareTo(Object o) {
        return 0;
    }
}