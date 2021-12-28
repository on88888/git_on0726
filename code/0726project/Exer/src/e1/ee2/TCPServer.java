package e1.ee2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        int number = 1;
        //1.创建服务器端对象
        ServerSocket serverSocket = new ServerSocket(8888);
        //2.不停的接受客户端请求
        while(true){
            //接受客户端请求
            Socket socket = serverSocket.accept();
            System.out.println("当前第" + number++ + "个客户端连接上来......");
            //获取客户端写过来的数据，并向客户端将数据反转后写出
            new Thread(new Runnable() {
                @Override
                public void run() {
                    BufferedReader br = null;
                    PrintStream ps = null;
                    try {
                        //读数据:一行一行读数据
                         br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        //写数据:一行一行写数据
                         ps = new PrintStream(socket.getOutputStream());
                        //一边读一边行
                        String line = null;
                        while((line = br.readLine()) != null){//读数据
                            //写数据 - 反转数据再写出
                            ps.println(new StringBuffer(line).reverse());//一定要用println方法。该方法会换行-有换行符就表示该行内容结束
                        }
                        System.out.println("连接已断开");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (br != null){
                            try {
                                br.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (ps != null){
                            ps.close();
                        }
                        if (socket != null){
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }).start();
        }
    }
}
