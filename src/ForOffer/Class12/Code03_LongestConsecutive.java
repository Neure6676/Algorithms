package ForOffer.Class12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。请你设计并实现时间复杂度为O(n) 的算法解决此问题。
 * 本题测试链接 : https://leetcode.com/problems/longest-consecutive-sequence/
 */
public class Code03_LongestConsecutive {

    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            }

        }
        return 1;
    }

}
