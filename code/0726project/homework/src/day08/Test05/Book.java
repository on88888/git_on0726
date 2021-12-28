package day08.Test05;

public class Book {
	private int id;
	private String title;
	private String author;
	private double price;
	private int sales;
	private int stock;
	private String imgPath = "E:/IDEA/常用快捷键.jpg";

	public Book() {
	}

	public Book(int id, String title, String author, double price, int sales, int stock) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.sales = sales;
		this.stock = stock;
	}

	public Book(int id, String title, String author, double price, int sales, int stock, String imgPath) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.sales = sales;
		this.stock = stock;
		this.imgPath = imgPath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getInfo(){
		return id + "\t" + title + "\t" + author + "\t" + price + "\t" + sales + "\t" + stock + "\t" + imgPath;
	}
}
