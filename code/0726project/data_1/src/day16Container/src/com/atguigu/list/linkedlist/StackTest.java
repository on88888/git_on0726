package day16Container.src.com.atguigu.list.linkedlist;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/*

栈：FILO
    first in last out

    LIFO
    last in first out

    罗盘子


 */
public class StackTest {



    @Test
    public void test02(){

        //多态
        Deque<String> stack = new LinkedList<>();

        //入栈
        stack.push("张三");
        stack.push("李四");
        stack.push("王五");

        //获取栈顶元素
        System.out.println("stack.peek() = " + stack.peek());

        //弹栈
        stack.pop();
       //获取栈顶元素
        System.out.println("stack.peek() = " + stack.peek());

        System.out.println(stack);


    }


    @Test
    public void test01(){

        Stack stack = new Stack();

        //入栈
        stack.push("张三");
        stack.push("李四");
        stack.push("王五");
        stack.push("赵六");

        //弹栈/出栈
        stack.pop();

        //获取栈顶元素
        Object peek = stack.peek();
        System.out.println("peek = " + peek);


    }

}
