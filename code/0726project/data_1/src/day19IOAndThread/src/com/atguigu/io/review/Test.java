package day19IOAndThread.src.com.atguigu.io.review;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/*


 */
class A implements Closeable {


    @Override
    public void close() throws IOException {

    }
}
/*
try(将创建的流对象

    此位置的对象 必须是 实现了 AutoCloseable 的接口才可以

){

    数据的读写

 }catch(异常){

}
 */
public class Test {

    public static void main(String[] args) {

        try (FileReader fr = new FileReader("D:/a.txt");
             A a = new A();

        ) {

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
