package UltimateAlgo2024.PrefixSum;

import java.util.HashMap;

// https://leetcode.com/problems/longest-well-performing-interval/
// >8 => 1; <=8 => -1
// sum >= 1
public class Code05_LongestWellPerformingInterval {

    public int longestWPI(int[] hours) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int i = 0, sum = 0; i < hours.length; i++) {
            int cur = hours[i] > 8 ? 1 : -1;
            sum += cur;
            if (sum > 0) {
                ans = i + 1;
            } else {
                if (map.containsKey(sum - 1)) {
                    ans = Math.max(ans, i - map.get(sum - 1));
                }
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return ans;
    }
}
