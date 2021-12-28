package day14API.src.com.atguigu.review;

import java.io.FileNotFoundException;

public class SexException extends FileNotFoundException {
    public SexException() {
    }
    public SexException(String s) {
        super(s);
    }
}
