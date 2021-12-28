package day21.java3;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        //1.创建客户端对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);
        //2.一边读一边写
        //创建写的流
        PrintStream ps = new PrintStream(socket.getOutputStream());
        //创建读（从服务器读）的流
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //读数据（从控制台）
        Scanner scanner = new Scanner(System.in);
        boolean isBreak = true;
        while(isBreak){
            //从控制台读取一行内容
            String line = scanner.nextLine();
            //如果读到stop就结束
            if ("stop".equals(line)){
                isBreak = false;
                break;
            }
            //将从控制台读取的数据写到服务器端
            ps.println(line);

            //读数据--从服务器端读取数据
            System.out.println(br.readLine());
        }

        //关资源
        ps.close();
        br.close();
        socket.close();
    }
}
