package day19;

import java.util.ArrayList;
import java.util.Collections;

public class exer1 {

	public static void main(String[] args) {
		String[] dian = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
		String[] hua = {"黑桃","红桃","方片","梅花"};
		String[] wang = {"大王","小王"};

		ArrayList<String> pp = new ArrayList<>();
		for (String s : dian) {
			for (String value : hua) {
				pp.add(value + s);
			}
		}
		//pp.dd()  pp这个集合添加元素
		Collections.addAll(pp, wang);
        //pp.size()  判断pp这个集合元素个数
		for (int i = 0; i < pp.size(); i++) {
			if ((i%10)==0){
				System.out.println();
			}
			//pp.get(index)  pp这个集合由下标(index)得出对应元素
			System.out.print(pp.get(i)+"  ");
		}
	}
}
