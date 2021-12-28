package day13ExceptionAndAPI.src.com.atguigu.exception.e5;

import java.io.FileNotFoundException;

public class AgeException extends FileNotFoundException {

    public AgeException(){}
    public AgeException(String message){
        super(message);
    }
}
