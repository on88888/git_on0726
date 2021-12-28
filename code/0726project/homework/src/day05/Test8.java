package day05;

public class Test8 {
    public static void main(String[] args) {
        char[] da = {'A','D','B','C','D'};
        char[] shang = {'D','C','B','A','D'};
        char[] gui = {'A','D','B','C','D'};
        char[] gu = {'A','D','B','C','A'};
        char[] hao = {'A','B','C','D','D'};
        int sums = 0;
        int sumg = 0;
        int sumgu = 0;
        int sumh = 0;
        for (int i = 0; i < da.length; i++) {
            if (shang[i] == da[i]){
                sums += 2;
            }
            if (gui[i] == da[i]) {
                sumg += 2;
            }
            if (gu[i] == da[i]) {
                sumgu += 2;
            }
            if (hao[i] == da[i]) {
                sumh += 2;
            }
        }
        System.out.println("满分10分，小尚：得分："+sums+"分");
        System.out.println("满分10分，小硅：得分："+sumg+"分");
        System.out.println("满分10分，小谷：得分："+sumgu+"分");
        System.out.println("满分10分，小好：得分："+sumh+"分");
    }
}
