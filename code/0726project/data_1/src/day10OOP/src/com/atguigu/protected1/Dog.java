package day10OOP.src.com.atguigu.protected1;

import day10OOP.src.com.atguigu.review.Animal;
//   protected 不同包下的【子类】可见
public class Dog extends Animal {
    public void show(){
        System.out.println(age);
        System.out.println(name);
       // System.out.println(color);
    }
}
