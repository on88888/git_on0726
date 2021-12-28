package e1.ee1;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {

    public static void main(String[] args) throws UnknownHostException {
        //获取本地主机的ip和名字
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);
        System.out.println(localHost.getHostAddress());//获取ip
        System.out.println(localHost.getHostName());//获取名字

        System.out.println("================================================");
        //通过域名获取对应的名称和ip
        InetAddress byName = InetAddress.getByName("www.baidu.com");
        System.out.println(byName);
    }
}
