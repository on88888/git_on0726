package day05;

public class Test3 {
    public static void main(String[] args) {
        String[] hua = {"黑桃","红桃","梅花","方片"};
        String[] dian = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        String[] puke = new String[hua.length*dian.length];
        //将花色和点数进行字符串连接存入puke中
        for(int i =0,k=0;i<=3;i++){
            for(int j=0;j<13;j++,k++){
                puke[k]=hua[i]+dian[j];
            }
        }
        System.out.print(puke[0]+" "+puke[4]+" "+puke[49]);
    }
}
