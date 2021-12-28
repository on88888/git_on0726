package day05Array.src;

public class IdeaTest1 {

    public static void main(String[] args) {
        l:
        for (int i = 2; i < 101; i++) {

            for (int j = 2; j < i; j++) {

                if (i % j == 0) {
                    continue l;
                }
            }

            System.out.println(i);

        }


    }
}
