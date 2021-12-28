package day15APIAndList.src.com.atguigu.enum1.exer1;

public enum Month {

    JANUARY(1,"1月份是1年的开始"),
    FEBRUARY(2,"2月份是1年的开始"),
    MARCH(3,"3月份是1年的开始"),
    APRIL(4,"4月份是1年的开始"),
    MAY(5,"5月份是1年的开始"),
    JUNE(6,"6月份是1年的开始"),
    JULY(7,"7月份是1年的开始"),
    AUGUST(8,"8月份是1年的开始"),
    SEPTEMBER(9,"9月份是1年的开始"),
    OCTOBER(10,"10月份是1年的开始"),
    NOVEMBER(11,"11月份是1年的开始"),
    DECEMBER(12,"12月份是1年的开始");
    //属性 描述
   private String desc;
   //月份值
   private int value;

 //  Month(){}
   Month(int value,String desc){
       this.value=value;
       this.desc=desc;
   }

    public static Month getByValue(int value){
        //获取所有的枚举对象
        Month[] months = Month.values();

        return months[value-1];

     //  return  Month.values()[value-1];

    }


    /*
    根据value获取枚举对象
     */
   public static Month getByValue1(int value){

       //获取所有的枚举对象
       Month[] months = Month.values();
       //让枚举对象的value属性 与传参过来的value进行对比

       for (Month month : months) {
           //两者一致代表找到了
           if(month.value==value){
               return month;
           }
       }
       return null;
   }

    @Override
    public String toString() {
        return value+" ---> "+desc;
    }
}
