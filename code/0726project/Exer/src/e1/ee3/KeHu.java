package e1.ee3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class KeHu {
    public static void main(String[] args) throws IOException {
        //客户端
        Socket socket = new Socket("192.168.18.22", 8888);
        OutputStream os = socket.getOutputStream();

        os.write("Hello DaHuang".getBytes());
        socket.shutdownOutput();

        InputStream is = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len = -1;
        while ((len = is.read(bytes))!=-1){
            System.out.println("on:"+new String(bytes,0,len));
        }

        os.close();
        socket.close();
    }

}
