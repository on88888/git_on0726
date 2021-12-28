package day15APIAndList.src.com.atguigu.enum1.jdk5a;
/*
jdk5之后
1.枚举中的属性 必须位于枚举元素的下面

2.构造器默认私有

3.自定义枚举类型 默认继承 Enum

4.枚举类型可以实现接口
     4.1 可以所有的枚举对象 共同使用一种处理结果
     4.2 可以 每一个枚举对象有单独的处理方式

常用方法：
    枚举类型.values();获取所有的枚举对象
    枚举类型.valueOf("name");获取指定的枚举对象

    枚举对象.ordinal();返回的是当前枚举对象的序列值 从0开始



 */
public enum Season  {

    SPRING("春天", "春暖花开"),
    SUMMER("夏天", "烈日炎炎"),
    AUTUMN("秋天", "秋高气爽"),
    WINTER("冬天", "寒风刺骨");

    private String seasonName;
    private String seasonDesc;


     Season(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}
