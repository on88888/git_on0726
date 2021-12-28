package day08.Test05;

public class TestBook {
	public static void main(String[] args) {

		Book[] all = new Book[5];
		all[0] = new Book(1, "《从入门到精通》", "张三", 88, 100, 1000,"E:/IDEA/常用快捷键.jpg");
		all[1] = new Book(2, "《从入门到放弃》", "李四", 89, 200, 800);
		all[2] = new Book(3, "《从入门到脱发》", "王五", 56, 10, 500);
		all[3] = new Book(4, "《从入门到吃药》", "赵六", 100, 180, 900);
		all[4] = new Book(5, "《从入门到升仙》", "钱七", 99, 0, 1000);

		System.out.println("排序前：");
		System.out.println("编号\t  书名\t\t作者  \t  价格\t销量\t库存\t图片");
		for (int i = 0; i < all.length; i++) {
			System.out.println(all[i].getInfo());
		}

		//排序
		for (int i = 1; i < all.length; i++) {
			for (int j = 0; j < all.length-i; j++) {
				if(all[j].getSales() < all[j+1].getSales()){
					Book temp = all[j];
					all[j] = all[j+1];
					all[j+1] = temp;
				}
			}
		}
		System.out.println("排序后：");
		System.out.println("编号\t  书名\t\t作者  \t  价格\t销量\t库存\t图片");
		for (int i = 0; i < all.length; i++) {
			System.out.println(all[i].getInfo());
		}
	}

}
