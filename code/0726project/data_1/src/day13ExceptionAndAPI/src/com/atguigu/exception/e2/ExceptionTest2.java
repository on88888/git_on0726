package day13ExceptionAndAPI.src.com.atguigu.exception.e2;
/*
抛出异常
    在方法内 使用 throw 异常对象;

 */
public class ExceptionTest2 {
    public static void main(String[] args) {

        Person p1 = new Person();
        p1.setName("张三");
        p1.setSex('B');

        System.out.println("p1 = " + p1);

    }

}
class Person{
  private   char sex;

  private   String name;

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        if(sex=='男'|| sex=='女') {
            this.sex = sex;
        }else {
          //  System.out.println("您输入的性别有问题 请重新输入");
            //手动抛出异常
            throw new ArrayIndexOutOfBoundsException("叫你输入男 不听是吧  后边也全错");
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "sex=" + sex +
                ", name='" + name + '\'' +
                '}';
    }
}