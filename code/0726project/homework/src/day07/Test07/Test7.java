package day07.Test07;

public class Test7 {
	public static void main(String[] args) {
		int[] all = {4,5,2,6,1};
		System.out.println("排序前：");
		MyArrays.print(all);

		MyArrays.sort(all);
		System.out.println("排序后：");
		MyArrays.print(all);


		int index = MyArrays.indexOf(all, 5);
		System.out.println("5在数组的下标是：" + index);

		System.out.println("新数组：");
		int[] copyArr = MyArrays.copy(all, 10);
		MyArrays.print(copyArr);
	}
}
