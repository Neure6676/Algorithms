package UltimateAlgo2024.Sort;

public class Code03_QuickSort {

    public static int[] sortArray(int[] nums) {
        if (nums.length > 1) {
            quickSort2(nums, 0, nums.length - 1);
        }
        return nums;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void quickSort2(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int x = arr[l + (int)(Math.random() * (r - l + 1))];
        partition2(arr, l, r, x);
        // 为了防止底层的递归过程覆盖全局变量
        // 这里用临时变量记录first、last
        int left = first;
        int right = last;
        quickSort2(arr, l, left - 1);
        quickSort2(arr, right + 1, r);
    }

    // 荷兰国旗问题
    public static int first, last;

    // 划分数组 <x放左边，==x放中间，>x放右边
    // 把全局变量first, last，更新成==x区域的左右边界
    public static void partition2(int[] arr, int l, int r, int x) {
        int first = l, last = r;
        int i = l;
        while (i <= last) {
            if (arr[i] == x) {
                i++;
            } else if (arr[i] < x) {
                swap(arr, first++, i++);
            } else {
                swap(arr, last--, i);  // i unchanged
            }
        }
    }






    public static void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int x = arr[l + (int) (Math.random() * (r - l + 1))];
        // return the idx of x, should be in somewhere of middle
        int mid = partition(arr, l, r, x);
        quickSort(arr, l, mid - 1);
        quickSort(arr, mid + 1, r);
    }

    // put <=x on the left, >x on the right
    public static int partition(int[] arr, int l, int r, int x) {
        // a : arr[l....a-1]范围是<=x的区域
        // xi : 记录在<=x的区域上任何一个x的位置，哪一个都可以
        int a = l, xi = 0;
        // arr[i] <= x: swap a and i, i++, a++
        // arr[i] > x: swap a and i, i++
        for (int i = l; i <= r; i++) {
            if (arr[i] <= x) {
                swap(arr, i, a);
                if (arr[a] == x) {
                    xi = a;
                }
                a++;
            }
        }
        swap(arr, xi, a - 1);
        return a - 1;
    }
}
