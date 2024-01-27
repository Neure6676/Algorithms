package UltimateAlgo2024.TwoPointers;

import java.util.Arrays;

// https://leetcode.com/problems/boats-to-save-people/
public class Code04_BoatsToSavePeople {

    public int numRescueBoats(int[] people, int limit) {
        int ans = 0;
        if (people.length != 0) {
            Arrays.sort(people);
            int l = 0, r = people.length - 1;
            while (l < r) {
                if (people[l] + people[r] > limit) {
                    if (people[l] >= people[r]) {
                        l++;
                    } else {
                        r--;
                    }
                } else {
                    l++;
                    r--;
                }
                ans++;
            }
            ans += l == r ? 1 : 0;
        }
        return ans;
    }

}
