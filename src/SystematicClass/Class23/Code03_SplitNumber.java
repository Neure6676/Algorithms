package SystematicClass.Class23;

/**
 * 给定一个正数n，求n的裂开方法数，
 * 规定：后面的数不能比前面的数小
 * 比如4的裂开方法有：
 * 1+1+1+1、1+1+2、1+3、2+2、4
 * 5种，所以返回5
 */
public class Code03_SplitNumber {

    public static int ways(int aim) {
        if (aim < 0) {    // 等于0的情况在递归中
            return 0;
        }
        if (aim == 1) {
            return 1;
        }
        return process(1, aim);
    }

    // 上一个拆出来的数是pre
    // 还剩rest需要去拆
    // 返回拆解的方法数
    public static int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int ways = 0;
        for (int first = pre; first <= rest; first++) {
            ways += process(first, rest - first);
        }
        return ways;
    }


    // 自己写的方法
    public static int ways1(int aim) {
        if (aim < 0) {    // 等于0的情况在递归中
            return 0;
        }
        if (aim == 1) {
            return 1;
        }
        return process1(1, aim);
    }

    public static int process1(int index, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (index > rest) {
            return 0;
        }
        int ways = 0;
        for (int item = 0; item * index <= rest; item++) {
            ways += process(index + 1, rest - item * index);
        }
        return ways;
    }


    // 动态规划
    public static int dp1(int n) {
        if (n < 0) {    // 等于0的情况在递归中
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        // dp[pre][rest]  pre范围1到n
        int[][] dp = new int[n + 1][n + 1];
        // 第0列与对角线
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        // pre > rest时为0，只使用右上三角
        // 注意看依赖，决定填写顺序
        // 从下往上，从左往右
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                int ways = 0;
                for (int first = pre; first <= rest; first++) {
                    ways += dp[first][rest - first];
                }
                dp[pre][rest] = ways;
            }
        }
        return dp[1][n];
    }

    // 继续优化：分析位置依赖
    // （x，y）与（x+1，y）
    public static int dp2(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre + 1][rest];
                dp[pre][rest] += dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }


    public static void main(String[] args) {
        int test = 39;
        System.out.println(ways(test));
        System.out.println(ways1(test));
        System.out.println(dp1(test));
        System.out.println(dp2(test));
    }

}
