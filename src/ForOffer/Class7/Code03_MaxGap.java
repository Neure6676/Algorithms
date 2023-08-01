package ForOffer.Class7;

/**
 * 给定一个数组arr，返回如果排序之后（注意是如果排序），相邻两数的最大差值。要求时间复杂度O(N)，不能使用非基于比较的排序
 * 测试链接 : https://leetcode.com/problems/maximum-gap/
 * 假设答案法
 */
public class Code03_MaxGap {

    public static int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        if (min == max) {  // 就一种数
            return 0;
        }
        // len + 1个桶
        boolean[] hasNum = new boolean[len + 1];
        int[] maxs = new int[len + 1];
        int[] mins = new int[len + 1];
        int bid = 0;
        for (int i = 0; i < len; i++) {
            bid = bucket(nums[i], len, min, max);
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
            hasNum[bid] = true;
        }
        int ans = 0;
        int lastMax = maxs[0]; // 上一个非空桶的最大值
        int i = 1;
        for (;i <= len; i++) {
            if (hasNum[i]) {
                ans = Math.max(mins[i] - lastMax, ans);
                lastMax = maxs[i];
            }
        }
        return ans;
    }

    /**
     *
     * @param num 当前数字
     * @param len 分成len + 1个桶
     * @param min
     * @param max
     * @return num该进第几号桶
     */
    public static int bucket(int num, int len, int min, int max) {
        // 一个桶的范围
        double range = (double) (max - min) / (double) len;
        // num和min之间的距离
        double distance = (double) (num - min);
        // 返回桶的编号，向下取整
        return (int) (distance / range);
    }
}
