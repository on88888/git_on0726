package day05;

public class Test4 {
    public static void main(String[] args) {
        char[] ccc = {'a','l','f','m','f','o','b','b','s','n'};
        int[] count = new int[26];  //count[]该数组目前二十六个值都默认为零
        for (char c : ccc) {
            count[c - 97]++;
        }
        for(int i=0; i<count.length; i++){
            if(count[i]!=0){
                System.out.println((char)(i+97) + "--" + count[i]);
            }
        }
    }
}
