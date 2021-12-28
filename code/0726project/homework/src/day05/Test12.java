package day05;

public class Test12 {
	public static void main(String[] args) {
		String[] arr = new String[10];
		java.util.Random rand = new java.util.Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = "";
			for (int j = 0; j < 6; j++) {
				int num;
				while(true){
					num = rand.nextInt(123);
					//数字[48,57]  大写字母[65,90]  小写字母[97,122]
					if(num>=48 && num<=57){
						break;
					}else if(num>=65 && num<=90){
						break;
					}else if(num>=97){    //x<=122可有可无；
						break;
					}
				}
				arr[i] += (char)num;
			}
		}

		for (String s : arr) {
			System.out.println("随机验证码：" + s);
		}
		/*for (int i = 0; i < arr.length; i++) {
			System.out.println("随机验证码：" + arr[i]);
		}*/
	}

}
