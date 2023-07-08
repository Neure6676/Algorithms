package SystematicClass.Class3;

/**
 * 不使用额外变量交换两个数
 */
public class Swap {
    /**
     * a = a ^ b
     * b = a ^ b
     * a = a ^ b
     */
    // i和j是一个位置的话，会出错
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


}
