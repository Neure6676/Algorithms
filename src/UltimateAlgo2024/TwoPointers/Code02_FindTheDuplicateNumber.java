package UltimateAlgo2024.TwoPointers;

// https://leetcode.com/problems/find-the-duplicate-number/
public class Code02_FindTheDuplicateNumber {

    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return -1;
        }
        // 快慢指针
        // 1. 使之相遇
        // f: f += 2
        // s: s += 1
        // 2. 快指针返回开头
        // f,s += 1
        int s = nums[0];
        int f = nums[nums[0]];
        while (s != f) {
            s = nums[s];
            f = nums[nums[f]];
        }
        f = 0;
        while (s != f) {
            s = nums[s];
            f = nums[f];
        }
        return s;
    }

}
