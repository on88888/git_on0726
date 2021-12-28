package e1.ee3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FuWuQi {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8888);
        while (true){

            Socket socket = serverSocket.accept();
            InputStream is = socket.getInputStream();

            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = is.read(bytes)) != -1){

                System.out.println("大黄： " + new String(bytes,0,len));
            }

            OutputStream os = socket.getOutputStream();
            os.write("hello on!! ".getBytes());
            socket.shutdownOutput();

            is.close();
            os.close();
            socket.close();
        }

    }
}
