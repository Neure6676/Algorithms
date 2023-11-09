package ForOffer.Class22;

/**
 * https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/
 */
public class Code01_MaximumSumOf3NonOverlappingSubarrays {

    // 中间遍历，再在两边分别找最大的两个
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int N = nums.length;
        int[] range = new int[N]; // 从i开始k个数sum
        int[] left = new int[N];  // 0-i范围上 从谁开始长度为k的子树组max sum
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i]; // 前k个数sum
        }
        range[0] = sum;  // 从0开始k个数sum
        // 先计算0-i范围上
        left[k - 1] = 0; //正好k个
        int max = sum;
        for (int i = k; i < N; i++) {
            sum = sum - nums[i - k] + nums[i]; // 前面减一个 后面加一个
            range[i - k + 1] = sum;
            left[i] = left[i - 1];
            if (sum > max) {
                max = sum;
                left[i] = i - k + 1;
            }
        }
        sum = 0;
        for (int i = N - 1; i >= N - k; i--) {
            sum += nums[i];
        }
        max = sum;
        // 计算i到n-1范围上
        int[] right = new int[N];
        right[N - k] = N - k; // 正好k个数
        for (int i = N - k - 1; i >= 0; i--) {
            sum = sum - nums[i + k] + nums[i];
            right[i] = right[i + 1];
            if (sum >= max) {
                max = sum;
                right[i] = i;
            }
        }
        int a = 0, b = 0, c = 0;
        max = 0;
        // 遍历中间
        for (int i = k; i < N - 2 * k + 1; i++) {
            int part1 = range[left[i - 1]];
            int part2 = range[i];
            int part3 = range[right[i + k]];
            int newSum = part1 + part2 + part3;
            if (newSum > max) {
                max = newSum;
                a = left[i - 1];
                b = i;
                c = right[i + k];
            }
        }
        return new int[] {a, b, c};
    }
}
