package day05;

public class Test10 {
    public static void main(String[] args) {
        java.util.Scanner input = new java.util.Scanner(System.in);
        System.out.println("请输入学员人数： ");
        int index = input.nextInt();
        String[] names = new String[index];
        int i = 0;
        do{
            System.out.println("请输入各学员姓名： ");
            String name = input.next();
            names[i] = name;
            i++;
        }while(i<index);
        for (String s : names) {
            System.out.println(s);
        }
        System.out.println("输入你要查找的学员姓名：");
        String cha = input.next();
        int Index= -1;
        for (int j = 0; j < index; j++) {
            if(cha.equals(names[j])){    //字符串的相等用  名称.equals(名称)  表示
                Index = j;
                break;
            }
        }
        if(Index!=-1){
            System.out.println("存在 他的下标是：" + Index);

        }else{
            System.out.println("查无此人");
        }
    }
}
