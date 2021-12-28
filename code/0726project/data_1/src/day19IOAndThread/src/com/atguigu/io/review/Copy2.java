package day19IOAndThread.src.com.atguigu.io.review;

import java.io.FileReader;
import java.io.FileWriter;

/*
try(){

}catch(){

}

 try(将创建的流对象){

    数据的读写
 }catch(异常){

}


 */
public class Copy2 {

    public static void main(String[] args) {

        //1.创建流对象
        try (
                FileReader fr = new FileReader("D:/a.txt");
                FileWriter fw = new FileWriter("D:/d.txt");
        ) {
            //2.完成数据读取
            char [] cs = new char[2];
            int len = 0;
            while ((len=fr.read(cs))!=-1){
                fw.write(cs, 0, len);
            }
            System.out.println("复制完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }



        //3.关流


    }
}
