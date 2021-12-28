package day21.java4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        //1.创建DatagramSocket对象
        DatagramSocket socket = new DatagramSocket(8989);
        //2.接收数据
        //2.1创建数据包对象
        /*
            DatagramPacket(byte buf[], int offset, int length)
            buf : 接收到的数据会存放到该数组中
            offset : 起始位置
            length : 长度
         */
        byte[] bytes = new byte[1024];
        DatagramPacket pac = new DatagramPacket(bytes,0,bytes.length);
        //2.2接收数据
        socket.receive(pac);
        //pac.getLength() : 存放数据的长度
        System.out.println(new String(bytes,0,pac.getLength()));
        //3.关闭资源
        socket.close();
    }
}
