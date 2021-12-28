package e1.ee1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/*
    基于TCP协议的Socket : 服务器端
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        //1.创建服务器端对象
        ServerSocket serverSocket = new ServerSocket(8888);
        //2.接收请求--服务器一个但是连接的客户端可以有n个
        while(true){
            //接收客户端请求--该对象用来和接收请求的客户端进行通信
            Socket socket = serverSocket.accept();
            //获取输入流
            InputStream is = socket.getInputStream();
            //读数据
            byte[] bytes = new byte[1024];
            int len = -1;
            while((len = is.read(bytes)) != -1){
                //将数组转成字符串
                System.out.println("龙哥:" + new String(bytes,0,len));
            }
            //给客户端回消息
            OutputStream os = socket.getOutputStream();//获取输出流
            os.write("hello long love you!!".getBytes());
            socket.shutdownOutput();//通知客户端写完了
            //关闭资源
            is.close();
            os.close();
            socket.close();
        }
    }
}
