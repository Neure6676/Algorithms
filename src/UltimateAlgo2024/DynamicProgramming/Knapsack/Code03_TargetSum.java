package UltimateAlgo2024.DynamicProgramming.Knapsack;

import java.util.HashMap;

// https://leetcode.com/problems/target-sum/
public class Code03_TargetSum {

    public int findTargetSumWays1(int[] nums, int target) {
        return f1(nums, 0, target);
    }

    public static int f1(int[] arr, int i, int rest) {
        if (i == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        return f1(arr, i + 1, rest - arr[i]) + f1(arr, i + 1, rest + arr[i]);
    }



    // 因为累加和可以为负数
    // 所以缓存动态规划表用哈希表!!!
    public int findTargetSumWays2(int[] nums, int target) {
        HashMap<Integer, HashMap<Integer, Integer>> dp = new HashMap<>();
        return f2(nums, 0, target, dp);
    }

    public static int f2(int[] arr, int i, int rest, HashMap<Integer, HashMap<Integer, Integer>> dp) {
        if (i == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        if (dp.containsKey(i) && dp.get(i).containsKey(rest)) {
            return dp.get(i).get(rest);
        }
        int ans = 0;
        ans += f2(arr, i + 1, rest - arr[i], dp) + f2(arr, i + 1, rest + arr[i], dp);
        dp.putIfAbsent(i, new HashMap<>());
        dp.get(i).put(rest, ans);
        return ans;
    }


    // 严格位置依赖的动态规划
    // 平移技巧
    public static int findTargetSumWays3(int[] nums, int target) {
        int s = 0;
        for (int num : nums) {
            s += num;
        }
        if (target < -s || target > s) {
            return 0;
        }
        int n = nums.length;
        // -s ~ +s -> 2 * s + 1
        int m = 2 * s + 1;
        // 原本的dp[i][j]含义:
        // nums[0...i-1]范围上，已经形成的累加和是sum
        // nums[i...]范围上，每个数字可以标记+或者-
        // 最终形成累加和为target的不同表达式数目
        // 因为sum可能为负数，为了下标不出现负数，
        // "原本的dp[i][j]"由dp表中的dp[i][j + s]来表示
        // 也就是平移操作！
        // 一切"原本的dp[i][j]"一律平移到dp表中的dp[i][j + s]
        int[][] dp = new int[n + 1][m];
        // 原本的dp[n][target] = 1，平移！
        dp[n][target + s] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = -s; j <= s; j++) {
                if (j + nums[i] + s < m) {
                    // 原本是 : dp[i][j] = dp[i + 1][j + nums[i]]
                    // 平移！
                    dp[i][j + s] = dp[i + 1][j + nums[i] + s];
                }
                if (j - nums[i] + s >= 0) {
                    // 原本是 : dp[i][j] += dp[i + 1][j - nums[i]]
                    // 平移！
                    dp[i][j + s] += dp[i + 1][j - nums[i] + s];
                }

            }
        }
        // 原本应该返回dp[0][0]
        // 平移！
        // 返回dp[0][0 + s]
        return dp[0][s];
    }

    // 新思路，转化为01背包问题
    // 思考1:
    // 虽然题目说nums是非负数组，但即使nums中有负数比如[3,-4,2]
    // 因为能在每个数前面用+或者-号
    // 所以[3,-4,2]其实和[3,4,2]会达成一样的结果
    // 所以即使nums中有负数，也可以把负数直接变成正数，也不会影响结果
    // 思考2:
    // 如果nums都是非负数，并且所有数的累加和是sum
    // 那么如果target>sum，很明显没有任何方法可以达到target，可以直接返回0
    // 思考3:
    // nums内部的数组，不管怎么+和-，最终的结果都一定不会改变奇偶性
    // 所以，如果所有数的累加和是sum，并且与target的奇偶性不一样
    // 那么没有任何方法可以达到target，可以直接返回0
    // 思考4(最重要):
    // 比如说给定一个数组, nums = [1, 2, 3, 4, 5] 并且 target = 3
    // 其中一个方案是 : +1 -2 +3 -4 +5 = 3
    // 该方案中取了正的集合为A = {1，3，5}
    // 该方案中取了负的集合为B = {2，4}
    // 所以任何一种方案，都一定有 sum(A) - sum(B) = target
    // 现在我们来处理一下这个等式，把左右两边都加上sum(A) + sum(B)，那么就会变成如下：
    // sum(A) - sum(B) + sum(A) + sum(B) = target + sum(A) + sum(B)
    // 2 * sum(A) = target + 数组所有数的累加和
    // sum(A) = (target + 数组所有数的累加和) / 2
    // 也就是说，任何一个集合，只要累加和是(target + 数组所有数的累加和) / 2
    // 那么就一定对应一种target的方式
    // 比如非负数组nums，target = 1, nums所有数累加和是11
    // 求有多少方法组成1，其实就是求，有多少种子集累加和达到6的方法，(1+11)/2=6
    // 因为，子集累加和6 - 另一半的子集累加和5 = 1（target)
    // 所以有多少个累加和为6的不同集合，就代表有多少个target==1的表达式数量
    // 至此已经转化为01背包问题了
    public static int findTargetSumWays4(int[] nums, int target) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum < target || ((target & 1) ^ (sum & 1)) == 1) {
            return 0;
        }
        return subsets(nums, (target + sum) >> 1);
    }

    // 求非负数组nums有多少个子序列累加和是t
    // 01背包问题(子集累加和严格是t) + 空间压缩
    // dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i]]
    public static int subsets(int[] nums, int t) {
        if (t < 0) {
            return 0;
        }
        int[] dp = new int[t + 1];
        dp[0] = 1;
        for (int num : nums) { // i省略了
            for (int j = t; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[t];
    }

}
