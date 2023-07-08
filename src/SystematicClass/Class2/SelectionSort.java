package SystematicClass.Class2;

public class SelectionSort {
    public static void selectionSort(int[] arr){
        if (arr == null || arr.length < 2)
            return;
        int N = arr.length;
        for (int i = 0; i<N; i++) {  //i从0开始
            int minValueIndex = i;
            for(int j = i+1; j<N; j++) // j++写成i++
                minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
            swap(arr, i, minValueIndex);
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
        selectionSort(arr);
        printArray(arr);
    }
}
