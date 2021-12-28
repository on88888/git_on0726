package day07.Test06;

public class Test6 {
	public static void main(String[] args) {
		int a = 5;
		int b = 3;
		System.out.println(a + "+" + b + "=" + MathTools.add(a, b));
		System.out.println(a + "-" + b + "=" + MathTools.subtract(a, b));
		System.out.println(a + "*" + b + "=" + MathTools.mutiply(a, b));
		System.out.println(a + "/" + b + "=" + MathTools.divide(a, b));
		System.out.println(a + "%" + b + "=" + MathTools.remainder(a, b));
		System.out.println(a + "," + b + "的最大值：" + MathTools.max(a, b));
		System.out.println(a + "," + b + "的最小值：" + MathTools.min(a, b));
		System.out.println(a + "==" + b + "？" + MathTools.equals(a, b));
		System.out.println(a + "是偶数？" + MathTools.isEven(a));
		System.out.println(a + "是素数？" + MathTools.isPrimeNumber(a));
		System.out.println("5.4四舍五入的结果：" + MathTools.round(5.4));
		System.out.println("5.6四舍五入的结果：" + MathTools.round(5.6));
	}
}
