package UltimateAlgo2024.TwoPointers;

import java.util.Arrays;

// https://leetcode.com/problems/heaters/
public class Code06_Heaters {

    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int ans = 0;
        for (int i = 0, j = 0; i < houses.length; i++) {
            while (j + 1 < heaters.length &&
                    // 距离一样时r++，有利于后面取得最小
                    (Math.abs(houses[i] - heaters[j]) >= Math.abs(houses[i] - heaters[j + 1]))) {
                j++;
            }
            ans = Math.max(ans, Math.abs(houses[i] - heaters[j]));
        }
        return ans;
    }
}
