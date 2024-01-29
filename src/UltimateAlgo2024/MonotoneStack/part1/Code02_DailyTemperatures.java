package UltimateAlgo2024.MonotoneStack.part1;

// https://leetcode.com/problems/daily-temperatures/
public class Code02_DailyTemperatures {

    public static int MAX = 100001;

    public static int[] stack = new int[MAX];

    public static int r;

    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        r = 0;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            // 相等时候的处理，相等也加入单调栈
            while (r > 0 && temperatures[stack[r - 1]] < temperatures[i]) {
                int cur = stack[--r];
                ans[cur] = i - cur;
            }
            stack[r++] = i;
        }
        return ans;
    }
}
