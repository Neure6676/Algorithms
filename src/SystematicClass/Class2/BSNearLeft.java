package SystematicClass.Class2;

public class BSNearLeft {

    //arr 有序的，找最左的>=num的数
    public static int mostLeftNum(int[] arr, int num) {
        if (arr == null || arr.length == 0)
            return -1;
        int L = 0;
        int R = arr.length - 1;
        int ans = -1;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (arr[mid] >= num) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return ans;
    }

    //arr 有序的，找最右的<=num的数
    public static int mostRightNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int ans = -1;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (arr[mid] <= num) {
                L = mid + 1;
                ans = mid;
            } else {
                R = mid - 1;
            }
        }
        return -1;
    }
}
