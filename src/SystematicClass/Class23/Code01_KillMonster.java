package SystematicClass.Class23;

/**
 * 给定3个参数，N，M，K
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次在[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 */
public class Code01_KillMonster {
    // 一共有(M + 1)^k种可能性
    // 提前死了也继续砍，到最后如果小于0就记一种（不减枝）
    public static double right1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow((M + 1), K);
        long kill = process1(N, M, K);
        return (double)((double)kill / (double)all);  // 注意这里的格式转化
    }

    // 返回可以砍死的情况数
    public static long process1(int restN, int M, int restK) {
        if (restK == 0) {
            return restN <= 0 ? 1 : 0;
        }
        if (restN <= 0) {
            return (long) Math.pow(M + 1, restK);
        }
        long ways = 0;
        for (int i = 0; i <= M; i++) {
            ways += process1(restN - i, M, restK - 1);
        }
        return ways;
    }

    // 动态规划
    public static double dp1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow((M + 1), K);
        long[][] dp = new long[K + 1][N + 1]; // 下标能取到N和K
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
                long ways = 0;
                for (int i = 0; i <= M; i++) {
                    if (hp - i >= 0) {
                        ways += dp[times - 1][hp - i];  // hp - i有可能越界, hp < 0
                    } else {
                        ways += (long) Math.pow(M + 1, times - 1);
                    }
                }
                dp[times][hp] = ways;
            }
        }
        long kill = dp[K][N];
        return (double)((double)kill / (double)all);  // 注意这里的格式转化
    }



    // dp1方法中2维表有枚举行为
    // 能不能通过邻近位置把枚举替代掉
    // dp[5][11] = dp[5][10] + dp[4][11] - dp[4][3]
    public static double dp2(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow((M + 1), K);
        long[][] dp = new long[K + 1][N + 1]; // 下标能取到N和K
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
                dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];
                if (hp - 1 - M >= 0) {
                    dp[times][hp] -= dp[times - 1][hp - 1 - M];
                } else {
                    dp[times][hp] -= Math.pow(M + 1, times - 1);  // 这个格子虽超出表的范围，但存在这个值，也需要剪掉
                }
            }
        }
        long kill = dp[K][N];
        return (double)((double)kill / (double)all);  // 注意这里的格式转化
    }

    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = right1(N, M, K);
            double ans2 = dp1(N, M, K);
            double ans3 = dp2(N, M, K);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
