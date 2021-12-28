package JDBC_Demo.jdbc4;

/*
    JavaBean
 */
public class Student {
    private int id;
    private String sname;
    private int age;

    public Student(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return id + " " + sname + " " + age;
    }
}
