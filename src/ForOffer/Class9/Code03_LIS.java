package ForOffer.Class9;

import java.util.HashMap;
import java.util.Map;

// 本题测试链接 : https://leetcode.com/problems/longest-increasing-subsequence
public class Code03_LIS {


    //O(N^2)子序列必须以i结尾 最长子序列是多长
    //看左边值比i小的当中，谁dp值最大

    //O(N*logN)：不通过遍历得到dp值
    //辅助数组end：end[i]：目前所有长度为i+1的递增子序列，结尾最小值是什么


    /**
     * Input: nums = [10,9,2,5,3,7,101,18]
     * Output: 4
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int[] ends = new int[N];
        ends[0] = nums[0];
        // ends有效区范围是0...right，right往右为无效区
        // 所以一开始right = 0, 表示有效区只有0...0范围
        int right = 0;
        int ans = 1;
        for (int i = 1; i < N; i++) {
            int l = 0;
            int r = right;
            while (l <= r) {
                int m = (l + r) / 2;
                if (nums[i] > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = nums[i];
            ans = Math.max(ans, l + 1);
        }
        return ans;
    }

}
