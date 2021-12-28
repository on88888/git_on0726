package day07.Test07;

public class test {
	static void print(int[] arr ){
		for (int i = 0; i < arr.length; i++) {
			if(i==0){
				System.out.print("["+arr[i]+",");
			}else if(i<arr.length-1){
				System.out.print(arr[i]+",");
			}else if(i==arr.length-1){
				System.out.print(arr[i]+"]");
			}
		}
		
	}

	public static void main(String[] args) {
		int[] ass = {9,8,7,6,5,4,3,2,1};
		System.out.print("排序前：");
		print(ass);
		System.out.println();
		sort(ass);
		System.out.print("排序后：");
		print(ass);
	}


	static void sort(int[] arr){
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = 0; j < arr.length-1-i; j++) {
				if(arr[j]>arr[j+1]){
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}


}
