package day17Container.src.com.atguigu.generic.g6;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Test {
    public static void main(String[] args) {
        Student<?> s = new Student<>();
      /*  s.setScore("优秀");
        s.setScore(100);*/
      //?代表任意类型  只能接受null
        s.setScore(null);

        // ? extends Number 可以是Number 也可以是Number的孩子 但是 孩子太多没有确定类型
        Student<? extends Number> s2 = new Student<>();
    /*    s2.setScore(100);
        s2.setScore(3.14);
        s2.setScore(new BigDecimal(3.14));*/
        s2.setScore(null);
        //? super Number 最小是Number 或者是Number的父亲

        Student<? super Number> s3 = new Student<>();

        s3.setScore(100);
        s3.setScore(3.14);
        s3.setScore(new BigInteger("20"));
    }
    @org.junit.Test
    public void test01(){
        Integer i1;//Integer extends Number
        BigInteger b;//BigInteger extends Number
        BigDecimal b1;//BigDecimal extends Number
        Double d;//Double extends Number


    }
}
class Student<T>{

    private String name;
    private T score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getScore() {
        return score;
    }

    public void setScore(T score) {
        this.score = score;
    }
}
