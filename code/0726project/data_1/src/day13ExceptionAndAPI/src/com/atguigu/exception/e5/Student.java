package day13ExceptionAndAPI.src.com.atguigu.exception.e5;

public class Student {

    private String name;
    private int age;
    private char sex;

    public Student() {
    }

    public Student(String name, int age, char sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws AgeException{
        if(age>=1 && age <=150){
            this.age = age;
        }else{
            throw new AgeException("年龄有问题");
        }



    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        if(sex=='男'|| sex=='女'){
            this.sex = sex;
        }else{

            throw new SexException("性别有问题 注意看清楚");
        }

    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
