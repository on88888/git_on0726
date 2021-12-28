package day07.Test07;

import java.util.Arrays;

public class MyArrays {

	static void sort(int[] arr){

		//java.util.Arrays.sort(arr);

//		for (int i = 0; i < arr.length; i++) {
//			int index = i;
//			for (int j = i+1; j < arr.length; j++) {
//				if(arr[index]>arr[j]){
//					index = j;
//				}
//			}
//			if(i!=index){
//				int temp = arr[index];
//				arr[index] = arr[i];
//				arr[i] = temp;
//			}
//		}

		for (int i = 0; i < arr.length-1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}

	}

	static int indexOf(int[]arr, int value){
		for (int i = 0; i < arr.length; i++) {
			if(value==arr[i]){
				return i;
			}
		}
		return value = -1;
	}

	static int[] copy(int[] arr, int len) {
		int[] narr = new int[len];
		for (int i = 0; i < narr.length && i < arr.length; i++) {
			narr[i] = arr[i];
		}
		return narr;
	}

	static void print(int[] arr){
//		System.out.print("[");
//		for (int i = 0; i < arr.length; i++) {
//			if(i==0){
//				System.out.print(arr[i]);
//			}else{
//				System.out.print("," + arr[i]);
//			}
//		}
//		System.out.println("]");
		System.out.println(Arrays.toString(arr));
	}

}
