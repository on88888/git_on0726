package day16Container.src.com.atguigu.list.linkedlist;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/*
队列：
    FIFO
    first in  first out

    超市结账

    入队

    出队

    获取队头元素


队头删除 队尾添加
 */
public class QueueTest {

    @Test
    public void test01(){

        Queue<String> queue = new LinkedList<>();

        //入队操作
        queue.add("张三");
        queue.add("李四");
        queue.add("王五");

        System.out.println("queue = " + queue);
        //获取队头元素
        System.out.println("queue.peek() = " + queue.peek());

        //出队
        queue.remove();

        queue.add("王安石");

        //获取队头元素
        System.out.println("queue.peek() = " + queue.peek());

        System.out.println("queue = " + queue);
    }
}
