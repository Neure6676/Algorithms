package LeetCode;

import java.util.HashMap;

public class twoSum {

    public int[] twoSum(int[] nums, int target) {
        // map<nums[i], i>
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[2];
        map.put(nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                ans[0] = i;
                ans[1] = map.get(target - nums[i]);
            }
            map.put(nums[i], i);
        }
        return ans;
    }
}
