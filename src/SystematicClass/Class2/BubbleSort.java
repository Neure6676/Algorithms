package SystematicClass.Class2;

public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;

        int N = arr.length;
        for (int end = N - 1; end >= 0; end--){
            for (int i = 0; i < end; i++){
                if (arr[i] > arr[i+1])
                    swap(arr, i, i+1);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tem = arr[i];
        arr[i] = arr[j];
        arr[j] = tem;
    }
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {7,3,4,2,5,6,7,1,8,3,9};
        printArray(arr);
        bubbleSort(arr);
        printArray(arr);
    }

}
