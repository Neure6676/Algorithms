package ForOffer.OA;

import java.util.TreeSet;

public class NotTwins {

    public static int notTwins(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i =0; i < N; i++) {
            if (!set.contains(arr[i])) {
                set.add(arr[i]);
            } else {
                set.remove(arr[i]);
            }
        }
        if (!set.isEmpty()) {
            return set.first();
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 3, 4, 4};
        System.out.println(notTwins(arr));
    }
}
