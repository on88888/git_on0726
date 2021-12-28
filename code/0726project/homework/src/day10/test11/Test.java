package day10.test11;

public class Test {
	public static void main(String[] args) {
		meeting(new Woman(),new Man(), new Woman(), new Man());
	}

	public static void meeting(Person... ps){
		for (int i = 0; i < ps.length; i++) {
			ps[i].eat();
			ps[i].toilet();
			if(ps[i] instanceof Woman){
				((Woman)ps[i]).makeup();
			}else if(ps[i] instanceof Man){
				((Man)ps[i]).smoke();
			}
		}
	}
}
