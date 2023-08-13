package ForOffer.Class12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。请你设计并实现时间复杂度为O(n) 的算法解决此问题。
 * 本题测试链接 : https://leetcode.com/problems/longest-consecutive-sequence/
 */
public class Code03_LongestConsecutive {

    // 表1：连续区间头 表2：连续区间尾
    // key: 开头/结尾 val: 长度
    // 5    8
    // 3    4
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> head = new HashMap<>();
        HashMap<Integer, Integer> tail = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();
        for (int num : nums) {
            if (!visited.contains(num)) {
                visited.add(num);
                head.put(num, 1);
                tail.put(num, 1);
                // 调整头
                if (tail.containsKey(num - 1)) {
                    int newLen = tail.get(num - 1) + 1;
                    int newHead = num - newLen + 1;
                    tail.put(num, newLen);
                    tail.remove(num - 1);
                    head.put(newHead, newLen);
                    head.remove(num);
                }
                // 头可能变了，尾肯定没变
                if (head.containsKey(num + 1)) {
                    int newHead = num - tail.get(num) + 1;
                    int newLen = head.get(num + 1) + tail.get(num);
                    int newTail = num + head.get(num + 1);
                    head.put(newHead, newLen);
                    head.remove(num + 1);
                    tail.put(newTail, newLen);
                    tail.remove(num);
                }
            }
        }
        int ans = 0;
        for (int len : head.values()) {
            ans = Math.max(ans, len);
        }
        return ans;
    }

}
