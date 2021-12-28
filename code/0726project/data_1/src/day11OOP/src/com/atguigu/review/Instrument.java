package day11OOP.src.com.atguigu.review;

public class Instrument {
    void play(){
        System.out.println("演奏乐器");
    }
}
class Piano extends Instrument{
    @Override
    void play() {
        System.out.println("弹 钢琴  ");
    }
}
class  ErHu extends Instrument{
    @Override
    void play() {
        System.out.println("二泉映月.....");
    }
}
class Guitar extends Instrument{
    @Override
    void play() {
        System.out.println("弹吉他.........");
    }
}