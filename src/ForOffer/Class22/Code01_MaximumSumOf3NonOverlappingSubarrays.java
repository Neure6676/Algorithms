package ForOffer.Class22;

/**
 * https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/
 */
public class Code01_MaximumSumOf3NonOverlappingSubarrays {

    // 中间遍历，再在两边分别找最大的两个
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int N = nums.length;
        int[] range = new int[N];
        int[] left = new int[N]; // 从哪个下标开始，之后k个数sum最大
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        range[0] = sum;  // 遍历
        // 先计算0-i范围上
        left[k - 1] = 0; //不够k个
        int max = sum;
        for (int i = k; i < N; i++) {  // right
            sum = sum - nums[i - k] + nums[i]; // 前面减一个 后面加一个
            range[i - k + 1] = sum;
            left[i] = left[i - 1];
            if (sum > max) {
                max = sum;
                left[i] = i - k + 1;
            }
        }
        sum = 0;

        // 计算i到n-1范围上
        int[] right = new int[N];
        right[N - k] = N - k;

        int a = 0, b = 0, c = 0;

        return new int[] {a, b, c};
    }
}
