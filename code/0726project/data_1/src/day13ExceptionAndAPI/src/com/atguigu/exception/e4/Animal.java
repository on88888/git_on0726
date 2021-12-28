package day13ExceptionAndAPI.src.com.atguigu.exception.e4;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Animal {

    public int getSum() throws FileNotFoundException {
        return 10;
    }

}

class Dog extends Animal{

    @Override
    public int getSum() throws FileNotFoundException {
        return super.getSum();
    }
}
