package ForOffer.Class27;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/two-sum/
public class Problem_0001_TwoSum {

    public int[] twoSum(int[] nums, int target) {
        // key: nums[i]  val: i
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[] {-1, -1};
    }
}
