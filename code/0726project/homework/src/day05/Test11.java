package day05;

import java.util.Scanner;

public class Test11 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("请输入一个单词");
		String str = in.next();
		char[] arr = str.toCharArray();//将字符串分成一组单个字符
		boolean flag = false;
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]=='a'){
				flag = true;
				index = i;
				break;
			}else{
				continue;
			}
		}
		if(flag){
			System.out.println("该单词存在字母a  ,下标是"+index);
		}else{
			System.out.println("该单词不存在字母a");
		}
	}
}
