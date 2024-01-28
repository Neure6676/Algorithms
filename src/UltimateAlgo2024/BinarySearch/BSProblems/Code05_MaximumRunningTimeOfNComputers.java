package UltimateAlgo2024.BinarySearch.BSProblems;

// https://leetcode.com/problems/maximum-running-time-of-n-computers/
// 碎片拼接：
public class Code05_MaximumRunningTimeOfNComputers {

    public long maxRunTime(int n, int[] batteries) {
        long sum = 0;
        for (int i : batteries) {
            sum += i;
        }
        long ans = 0;
        for (long l = 0, r = sum, m; l <= r;) {
            m = l + (r - l) / 2;
            if (f(batteries, n, m)) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    public static boolean f(int[] arr, long n, long m) {
        // 碎片电池的sum
        long sum = 0;
        for (int i : arr) {
            if (i >= m) {
                n--;
            } else {
                sum += i;
            }
            if (sum >= (n * m)) {
                return true;
            }
        }
        return false;
    }

    // 二分答案法 + 增加分析(贪心)
    // 提交时把函数名改为maxRunTime
    // 时间复杂度O(n * log(max))，额外空间复杂度O(1)
    public static long maxRunTime2(int num, int[] arr) {
        int max = 0;
        long sum = 0;
        for (int x : arr) {
            max = Math.max(max, x);
            sum += x;
        }
        // 就是增加了这里的逻辑
        if (sum > (long) max * num) {
            // 所有电池的最大电量是max
            // 如果此时sum > (long) max * num，
            // 说明 : 最终的供电时间一定在 >= max，而如果最终的供电时间 >= max
            // 说明 : 对于最终的答案X来说，所有电池都是课上讲的"碎片拼接"的概念
            // 那么寻找 ? * num <= sum 的情况中，尽量大的 ? 即可
            // 即sum / num
            return sum / num;
        }
        // 最终的供电时间一定在 < max范围上
        // [0, sum]二分范围，可能定的比较粗，虽然不影响，但毕竟是有点慢
        // [0, max]二分范围！更精细的范围，二分次数会变少
        int ans = 0;
        for (int l = 0, r = max, m; l <= r;) {
            m = l + ((r - l) >> 1);
            if (f2(arr, num, m)) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    public static boolean f2(int[] arr, int num, int time) {
        // 碎片电量总和
        long sum = 0;
        for (int x : arr) {
            if (x > time) {
                num--;
            } else {
                sum += x;
            }
            if (sum >= (long) num * time) {
                return true;
            }
        }
        return false;
    }


}
