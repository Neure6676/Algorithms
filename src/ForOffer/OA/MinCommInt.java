package ForOffer.OA;

import java.util.Arrays;
import java.util.TreeSet;

public class MinCommInt {

    public static int[] minCommInt(int n, int[] data) {
        int[] ans = new int[n];

        for (int k = 1; k <= n; k++) {
            // at least appears (n - k + 1) times
            int[] count = new int[n + 1];
            // subarrays
            for (int s = 0; s <= n - k; s++) {
                TreeSet<Integer> set = new TreeSet<>();
                for (int i = s; i <= s + k - 1; i++) {
                    set.add(data[i]);
                }
                for (Integer e : set) {
                    count[e]++;
                }
            }
            // got count
            int maxCount = 0;
            int minNum = 0;
            for (int i = 1; i <= n; i++) {
                if (count[i] > maxCount) {
                    maxCount = count[i];
                    minNum = i;
                }
            }
            ans[k - 1] = maxCount < n - k + 1 ? -1 : minNum;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] data = {4, 3, 3, 4, 2};
        System.out.println(Arrays.toString(minCommInt(5, data)));
    }
}
