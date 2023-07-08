package SystematicClass.Class15;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入正数数组costs、正数数组profits、正数K和正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明：每做完一个项目，马上获得的收益，可以支持你去做下一个项目，不能并行的做项目。
 * 输出：最后获得的最大钱数
 */
public class Code04_IPO {
    // 最多K个项目
    // W是初始资金
    // Profits Capital一定等长
    // 返回最终最大的资金
    public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
        PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < Profits.length; i++) {
            minCostQ.add(new Program(Profits[i], Capital[i]));  // 实例化时前面要加new
        }
        for (int i = 0; i < K; i++) {
            while (!minCostQ.isEmpty() && minCostQ.poll().c <= W) {
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()) {
                return W;
            }
            W += maxProfitQ.poll().p;
        }
        return W;
    }

    private static class Program {
        private int p;
        private int c;

        public Program(int _p, int _c) {
            p = _p;
            c = _c;
        }
    }

    private static class MinCostComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.c - o2.c;
        }
    }

    private static class MaxProfitComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o2.p - o1.p;
        }
    }
}
