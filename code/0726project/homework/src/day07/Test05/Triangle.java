package day07.Test05;

public class Triangle {
	double a;
	double b;
	double c;

	boolean isRightTriangle(){
		if(a>0&&b>0&&c>0){
			//是否能构成三角形
			if(a+b>c&&a+c>b&&b+c>a){
				if(a*a+b*b==c*c||a*a+c*c==b*b||b*b+c*c==a*a){
					return true;
				}
			}
		}
		return false;
	}
	boolean isIsoscelesTriangle(){
		if (a > 0 && b > 0 && c > 0) {
			// 判断是否是三角形
//		if(a==b||a==c||b==c){
//			return true;
//		}
//		return false;
			if (a + b > c && a + c > b && b + c > a) {
				return a == b || a == c || b == c;
			}
		}
		return false;
	}
	boolean isEquilateralTriangle(){
		if (a > 0 && b > 0 && c > 0) {
			// 判断是否是三角形
			if (a + b > c && a + c > b && b + c > a) {
				return a == b && b == c;
			}
		}
		return false;
	}
	double getArea() {
		// 判断值合法
		if (a > 0 && b > 0 && c > 0) {
			// 判断是否是三角形
			if (a + b > c && a + c > b && b + c > a) {
				double p = (a + b + c) / 2;
				return Math.sqrt(p * (p - a) * (p - b) * (p - c));
			}
		}
		return 0;
	}
	double getLength(){
		if (a > 0 && b > 0 && c > 0) {
			// 判断是否是三角形
			if (a + b > c && a + c > b && b + c > a) {
				return a + b + c;
			}
		}
		return 0;
	}
}


