package UltimateAlgo2024.BinarySearch.BSProblems;

// https://leetcode.com/problems/koko-eating-bananas/
public class Code01_KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {
        int max = piles[0];
        for (int i : piles) {
            max = Math.max(max, i);
        }
        int min = 1, ans = 0, mid = 0;
        while (min <= max) {
            long take = 0;
            mid = min + ((max - min) / 2);
            for (int i : piles) {
                take += (i + mid - 1) / mid;
            }
            if (take > h) {
                min = mid + 1;
            } else {
                max = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }
}
