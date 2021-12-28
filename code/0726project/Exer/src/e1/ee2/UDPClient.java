package e1.ee2;

import java.io.IOException;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        //1.创建DatagramSocket对象
        DatagramSocket socket = new DatagramSocket();
        //2.发送数据
        //2.1创建数据包
        /*
         DatagramPacket(byte buf[], int offset, int length,
                          InetAddress address, int port)
          buf : 发送的数据
          offset ：起始位置
          length ：长度（偏移量）
          address ： 接收数据的地址
          port : 端口号
         */
        String s = "hello java";
        DatagramPacket packet = new DatagramPacket(s.getBytes(), 0, s.length(),
                InetAddress.getLocalHost(), 8989);
        //2.2
        socket.send(packet);
        //3.关闭资源
        socket.close();
    }
}
