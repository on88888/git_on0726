package day21.java2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/*
    基于TCP协议的Socket : 客户端
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        //1.创建客户端对象
        /*
            Socket(String host, int port)
            host : 服务器主机地址（ip）
            port : 服务器端口号
         */
        Socket socket = new Socket("192.168.18.76", 8888);

        //2.客户端给服务器端发消息---通过流
        //2.1创建输出流
        OutputStream os = socket.getOutputStream();
        //2.2写数据 ---- （注意：写和读如果都是字节流不要写中文--可以将字节流转成字符（通过转换流））
        os.write("hello cang".getBytes());
        //2.3通知服务器写完了---如果不通知服务器就会一直等待（阻塞）
        socket.shutdownOutput();

        //接收服务器端的消息
        //获取输入流
        InputStream is = socket.getInputStream();
        //读数据
        byte[] bytes = new byte[1024];
        int len = -1;
        while((len = is.read(bytes)) != -1){
            //将数组转成字符串
            System.out.println("苍姐:" + new String(bytes,0,len));
        }

        //3.关闭资源
        os.close();
        socket.close();
    }
}
