package SystematicClass.Class21;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 业务限制模型！
 *
 * 给定一个数组arr，arr[i]代表第i号咖啡机泡一杯咖啡的时间
 * 给定一个正数N，表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡
 * 只有一台咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯（串行 synchronous）
 * 每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
 * 假设所有人拿到咖啡之后立刻喝干净，
 * 返回从开始等到所有咖啡机变干净的最短时间
 * 三个参数：int[] arr、int N，int a、int b
 */
public class Code03_Coffee {
    // 思路：*小根堆模拟排序*
    public static class Machine {
        public int timePoint; // 能再次接客的时间
        public int workTime;  // 泡一杯咖啡的时间

        public Machine(int t, int w) {
            timePoint = t;
            workTime = w;
        }
    }

    public static class MyComparator implements Comparator<Machine> {

        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
        }
    }

    public static int minTime(int[] arr, int n, int wash, int air) {
        PriorityQueue<Machine> heap = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            heap.add(new Machine(0, arr[i]));   // 新的对象生成，不要忘记new！！！
        }
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Machine cur = heap.poll();
            cur.timePoint += cur.workTime;
            drinks[i] = cur.timePoint;  //i号小人喝完的时间
            heap.add(cur);
        }
        return bestTime(drinks, wash, air, 0, 0);
    }

    /**
     * 暴力递归
     * @param drinks: 所有人开始洗的时间
     * @param wash：洗需要的时间
     * @param air：挥发需要的时间
     * @param index：来到第几个人
     * @param free：洗杯器下次空闲的时间
     * @return 最好时间
     */
    public static int bestTime(int[] drinks, int wash, int air, int index, int free) {
        if (index == drinks.length) {
            return 0;
        }
        // 洗第index号杯子. 注意开始洗的时间是喝完时间和下个洗杯器空闲时间的较大值
        int selfClean1 = Math.max(drinks[index], free) + wash;
        int restClean1 = bestTime(drinks, wash, air, index + 1, selfClean1);  // 上面的依赖下面的，从下往上填
        int ans1 = Math.max(selfClean1, restClean1);
        // 挥发第index号杯子
        int selfClean2 = drinks[index] + air;
        int restClean2 = bestTime(drinks, wash, air, index + 1, free);
        int ans2 = Math.max(selfClean2, restClean2);
        return Math.min(ans1, ans2);
    }


    // dp
    // index 0～N
    // free：范围无法确定: 业务限制模型，需要认为想限制：限制不够，业务来凑
    public static int bestTimeDp(int[] drinks, int wash, int air) {
        int N = drinks.length;
        // 确定free的最大值
        int maxFree = wash;
        for (int i = 1; i < N; i++) {
            maxFree = Math.max(drinks[i], drinks[i - 1] + wash) + wash;
        }
        int[][] dp = new int[N + 1][maxFree + 1];
        // dp[N][..] = 0
        for (int index = N - 1; index >= 0; index--) {  // 注意，上面的依赖下面的，从下往上填
            for (int free = 0; free <= maxFree; free++) {
                int selfClean1 = Math.max(drinks[index], free) + wash;
                if (selfClean1 > maxFree) { // 防止越界
                    continue;
                }
                int restClean1 = dp[index + 1][selfClean1];
                int ans1 = Math.max(selfClean1, restClean1);
                // 挥发第index号杯子
                int selfClean2 = drinks[index] + air;
                int restClean2 = dp[index + 1][free];
                int ans2 = Math.max(selfClean2, restClean2);
                dp[index][free] = Math.min(ans1, ans2);
            }
        }
        return dp[0][0];
    }



}
