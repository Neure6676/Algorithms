package SystematicClass.Class5;

/**
 * 在一个数组中，任何一个前面的数a，和任何一个后面的数b，如果(a,b)是降序的，就称为降序对
 * 给定一个数组arr，求数组的降序对总数量
 *
 * 要求的是右边有多少个数比他小 -> merge的时候从右开始遍历
 * 左右相同时要先拷贝右
 */
public class ReversePair {

    public static int reverPairNum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = (l + r) / 2;
        return process(arr, l, mid) +
                process(arr, mid +1, r) +
                merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int res = 0;
        int i = 0;
        int p1 = mid;
        int p2 = r;
        while (p1 >= l && p2 >= mid + 1) {
            res += arr[p1] > arr[p2] ? (p2 - (mid + 1) + 1) : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l) {
            help[i--] = arr[p1--];
        }
        while (p2 >= (mid+1)) {
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            arr[l+i] = help[i];
        }
        return res;
    }

}
