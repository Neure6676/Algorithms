package UltimateAlgo2024.MonotoneQueue.Part2;

import java.util.Arrays;

// https://leetcode.com/problems/maximum-number-of-tasks-you-can-assign/
public class Code03_MaximumNumberOfTasksYouCanAssign {

    public static int[] tasks;

    public static int[] workers;

    public static int MAX = 50001;

    public static int[] deque = new int[MAX];

    public static int h, t;

    public int maxTaskAssign(int[] ts, int[] ws, int pills, int strength) {
        tasks = ts;
        workers = ws;
        Arrays.sort(tasks);
        Arrays.sort(workers);
        int tsize = tasks.length;
        int wsize = workers.length;
        int ans = 0;
        for (int l = 0, r = Math.min(tsize, wsize), m; l <= r;) {
            m = l + (r - l) / 2;
            if (f(0, m - 1, wsize - m, wsize - 1, strength, pills)) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    // tasks[tl....tr]需要力量最小的几个任务
    // workers[wl....wr]力量值最大的几个工人
    // 药效是s，一共有的药pills个
    // 在药的数量不超情况下，能不能每个工人都做一个任务
    public static boolean f(int tl, int tr, int wl, int wr, int s, int pills) {
        h = t = 0;
        // 已经使用的药的数量
        int cnt = 0;
        for (int i = wl, j = tl; i <= wr; i++) {
            // i : 工人的编号
            // j : 任务的编号
            for (; j <= tr && tasks[j] <= workers[i]; j++) {
                // 工人不吃药的情况下，去解锁任务
                deque[t++] = j;
            }
            if (h < t && tasks[deque[h]] <= workers[i]) {
                h++;
            } else {
                // 吃药之后的逻辑
                for (; j <= tr && tasks[j] <= workers[i] + s; j++) {
                    deque[t++] = j;
                }
                if (h < t) {
                    cnt++;
                    t--;
                } else {
                    return false;
                }
            }
        }
        return cnt <= pills;
    }
}
