package day06Array.src;

public class Review {
    public static void main(String[] args) {
        int[] arr = new int[10];

        for (int i = 0; i < arr.length; i++) {
            int a = (int) (Math.random() * (29 - 20 + 1) + 29);
            arr[i] = a;
        }
        Review review = new Review();
        int[] ints = review.popSort(arr);
        for (int anInt : ints) {
            System.out.println(anInt);

        }
    }

    public int[] popSort(int[] arr){
        for (int end = arr.length - 1; end > 0; end--) {
            boolean flag = true;
            for (int begin = 1; begin <= end; begin++) {
                if (arr[begin] > arr[begin - 1]){
                    int temp = arr[begin];
                    arr[begin] = arr[begin - 1];
                    arr[begin - 1] = temp;
                    flag = false;
                }
            }
            if (flag){
                break;
            }

        }
        return arr;
    }
}
