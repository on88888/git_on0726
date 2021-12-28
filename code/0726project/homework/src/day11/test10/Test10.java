package day11.test10;
/*
 * 1、final
 * final修饰的是o，不是i，因此o变量的值不能修改，不是说i变量的值不能修改
 * 2、方法的参数传递机制：
 * 形参是基本数据类型，那么实参给形参的是数据值的副本，形参的修改不影响实参；
 * 形参是引用数据类型，那么实参给形参的是地址值的副本，形参对象修改属性相当于实参对象修改属性
 */
public class Test10 {
	public static void main(String[] args) {

		Other o = new Other();
		new Test10().addOne(o);
		System.out.println(o.i);
	}

	public void addOne(final Other o){
		o.i++;
	}
}
class Other{
	public int i;
}
