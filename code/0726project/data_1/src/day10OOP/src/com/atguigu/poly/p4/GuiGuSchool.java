package day10OOP.src.com.atguigu.poly.p4;

public class GuiGuSchool {
    public static Programmer produceProgrammer(char c) {
        if(c=='C'){
            return new China();
        }else if(c=='R'){
            return new Russia();
        }else if(c=='J'){
            return new Japan();
        }
        return null;
    }
}
