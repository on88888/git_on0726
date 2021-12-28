package day07.Test5;

public class Triangle {
	double a;
	double b;
	double c;

	// 判断是否是一个直角三角形
	boolean isRightTriangle() {
		// 判断值合法
		if (a > 0 && b > 0 && c > 0) {
			// 判断是否是三角形
			if (a + b > c && a + c > b && b + c > a) {
				if (a * a + b * b == c * c || b * b + c * c == a * a || a * a + c * c == b * b) {
					return true;
				}
			}
		}
		return false;
	}

	boolean isIsoscelesTriangle() {
		// 判断值合法
		if (a > 0 && b > 0 && c > 0) {
			// 判断是否是三角形
			if (a + b > c && a + c > b && b + c > a) {
				// 判断是否是等腰三角形
				if (a == b || b == c || a == c) {
					return true;
				}
			}
		}
		return false;
	}

	boolean isEquilateralTriangle() {
		// 判断值合法
		if (a > 0 && b > 0 && c > 0) {
			// 判断是否是三角形
			if (a + b > c && a + c > b && b + c > a) {
				// 判断是否是等边三角形
				if (a == b && b == c) {
					return true;
				}
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

	double getLength() {
		// 判断值合法
		if (a > 0 && b > 0 && c > 0) {
			// 判断是否是三角形
			if (a + b > c && a + c > b && b + c > a) {
				return a + b + c;
			}
		}
		return 0;
	}
}
