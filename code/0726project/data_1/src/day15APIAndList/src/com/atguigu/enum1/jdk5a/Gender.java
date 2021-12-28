package day15APIAndList.src.com.atguigu.enum1.jdk5a;

public enum Gender implements Run{

    MAN{
        @Override
        public void walk() {
            System.out.println("男生 大步流星走");
        }
    },WOMAN{
        @Override
        public void walk() {
            System.out.println("女生 婀娜多姿走");
        }
    };


}
interface Run{
    void walk();
}
