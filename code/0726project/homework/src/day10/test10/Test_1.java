package day10.test10;

public class Test_1 {
	public static void main(String[] args) {
		Graphic[] all = new Graphic[3];
		all[0] = new Circle(2);
		all[1] = new Rectangle(2, 3);
		all[2] = new Triangle(3, 4, 5);
		System.out.println("排序前：");
		print(all);
		sort(all);
		System.out.println("排序后：");
		print(all);

	}

	public static void print(Graphic a[]){
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i].getInfo());
		}
	}

	public static void sort(Graphic a[]){
		for (int i = 0; i < a.length-1; i++) {
			for (int j = 0; j < a.length-i-1; j++) {
				if(a[i].getArea()>a[i+1].getArea()) {
					Graphic temp = a[i];
					a[i] = a[i + 1];
					a[i + 1] = temp;
				}

			}
		}
	}

	/*public static void print(Graphic[] all){
		for (int i = 0; i < all.length; i++) {
			System.out.println(all[i].getInfo());
		}
	}

	public static void sort(Graphic[] all){
		for (int i = 1; i < all.length; i++) {
			for (int j = 0; j < all.length-i; j++) {
				if(all[j].getArea() > all[j+1].getArea()){
					Graphic temp = all[j];
					all[j] = all[j+1];
					all[j+1] = temp;
				}
			}
		}
	}*/
}
