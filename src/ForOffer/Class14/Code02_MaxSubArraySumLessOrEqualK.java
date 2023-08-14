package ForOffer.Class14;


import java.util.TreeSet;

public class Code02_MaxSubArraySumLessOrEqualK {

    // 请返回arr中，求个子数组(连续)的累加和，是<=K的，并且是最大的。
    // 返回这个最大的累加和
    public static int getMaxLessOrEqualK(int[] arr, int K) {
        // 求0到i上<=K的，并且是最大的，就等价与求这个范围前缀和>=(sum-k)且最小的
        // 放前缀和
        TreeSet<Integer> set = new TreeSet<>();
        // 一个数也没有的时候，就已经有一个前缀和是0了 非常重要！！
        set.add(0);
        int ans = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i];
            if (set.ceiling(sum - K) != null) {
                ans = Math.max(ans, sum - set.ceiling(sum - K));
            }
            set.add(sum);
        }
        return ans;
    }
}
