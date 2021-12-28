package day09OOP.src.com.atguigu.rescursion.r2;
/*
猴子吃桃子问题，猴子第一天摘下若干个桃子，
当即吃了所有桃子的一半，还不过瘾，又多吃了一个。第二天又将仅剩下的桃子吃掉了一半，又多吃了一个。
以后每天都吃了前一天剩下的一半多一个。到第十天，只剩下一个桃子。试求第一天共摘了多少桃子？
设第九天 有x个桃子
    x/2 - 1 = 1
     x/2 = 1+1; 第十天的桃子数+1
     x = 4;  (第十天的桃子数+1)*2

 设第八天 有x个桃子
 x/2 -1 = 4;
     x/2  = 5;第九天的桃子数+1
        x = 10; (第九天的桃子数+1)*2

  设第七天 有x个桃子
  x/2 -1 = 10;
  x/2 = 11; 第八天的桃子数+1
  x = 22;（第八天的桃子数+1）*2；




 */
public class Eat {

    public static int eat(int day){

        if(day==10){
            return 1;
        }
        return (eat(day+1)+1)*2;
    }

    public static void main(String[] args) {
        System.out.println("eat(9) = " + eat(9));
        System.out.println("eat(1) = " + eat(1));
    }
}
