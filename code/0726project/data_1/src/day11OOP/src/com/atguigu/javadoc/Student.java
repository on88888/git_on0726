package day11OOP.src.com.atguigu.javadoc;

public class Student {

    private String name;
    private int age;

    /**
     * 无参构造器
     */
    public Student() {
    }

    /**
     * 两个参数的构造器
     * @param name 名字
     * @param age 年龄
     */
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * 获取名字
     * @return 名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名字
     * @param name 名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取年龄
     * @return 年龄
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置年龄
     * @param age 年龄
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 求和
     * @param a 第一个加数
     * @param b 第二个加数
     * @return 和
     */
    public int getSum(int a,int b){
        return a+b;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
