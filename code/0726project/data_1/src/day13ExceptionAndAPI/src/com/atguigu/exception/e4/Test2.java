package day13ExceptionAndAPI.src.com.atguigu.exception.e4;

import java.io.FileNotFoundException;
import java.io.IOException;

/*
子类重写父类的方法
权限修饰符  子类访问权限 >=父类的访问权限

返回值类型
        基本数据类型 必须与父类一致
        引用数据类型 可以是父类 也可以是父类的子类
方法名 形参列表 必须与父类一致

异常：
    父类抛出的是运行时异常  子类抛出异常没有约束
    父类抛出的是编译时异常  子类不能抛出比父类更大的异常

注意：
    Exception 是编译时异常
 */
public class Test2 {

    public static void main(String[] args) {


      //  ArrayIndexOutOfBoundsException;

      //  FileNotFoundException;

      //  show();
    }

    private static void show()throws Exception {


        throw new Exception();
    }

}
