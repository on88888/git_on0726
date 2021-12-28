package day22.java;

/*
    自定义注解:

    格式：
        @interface 注解名{

        }
 */
public @interface MyAnn {
    String value() default "longge";//理解成属性
}
