package day08.Test04;

public class Card {
	String hh;
	char dd;
	public Card(String hh,char dd){
		this.hh = hh;
		this.dd = dd;
	}

	public Card() {
	}

	public String getHh() {
		return hh;
	}

	public void setHh(String hh) {
		this.hh = hh;
	}

	public char getDd() {
		return dd;
	}

	public void setDd(char dd) {
		this.dd = dd;
	}

	public void showCard(){
		System.out.println(hh + dd);
	}
}
