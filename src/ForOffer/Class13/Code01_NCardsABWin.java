package ForOffer.Class13;

import java.text.DecimalFormat;

/**
 * 谷歌面试题扩展版，面值为1~N的牌组成一组，每次你从组里等概率的抽出1~N中的一张，下次抽会换一个新的组，有无限组
 * 当累加和<a时，你将一直抽牌；当累加和>=a且<b时，你将获胜；当累加和>=b时，你将失败
 * 返回获胜的概率，给定的参数为N，a，b
 */
public class Code01_NCardsABWin {

    // 谷歌面试题
    // 面值为1~10的牌组成一组，
    // 每次你从组里等概率的抽出1~10中的一张
    // 下次抽会换一个新的组，有无限组
    // 当累加和<17时，你将一直抽牌
    // 当累加和>=17且<21时，你将获胜
    // 当累加和>=21时，你将失败
    // 返回获胜的概率
    public static double f1() {
        return p1(0);
    }

    // 当你来到cur这个累加和的时候，获胜概率是多少返回！
    public static double p1(int cur) {
        if (cur >= 21) {
            return 0.0;
        }
        if (cur >= 17) {
            return 1.0;
        }
        double ans = 0.0;
        for (int i = 1; i <= 10; i++) {
            ans = ans + (0.1 * p1(cur + i)); // 每种情况出现的可能性是1/10
        }
        return ans;
    }


    // 谷歌面试题扩展版
    // 面值为1~N的牌组成一组，
    // 每次你从组里等概率的抽出1~N中的一张
    // 下次抽会换一个新的组，有无限组
    // 当累加和<a时，你将一直抽牌
    // 当累加和>=a且<b时，你将获胜
    // 当累加和>=b时，你将失败
    // 返回获胜的概率，给定的参数为N，a，b
    public static double f2(int N, int a, int b) {
        if (N < 1 || a >= b || a < 0 || b < 0) {
            return 0.0;
        }
        // 赢的区间太长，不管怎么抽最后一张都能赢
        if (b - a >= N) {
            return 1.0;
        }
        return p2(N, 0, a, b);
    }

    public static double p2(int N, int cur, int a, int b) {
        if (cur >= b) {
            return 0.0;
        }
        if (cur >= a) {
            return 1.0;
        }
        double ans = 0.0;
        for (int i = 1; i <= N; i++) {
            ans += p2(N, cur + i, a, b);
        }
        return ans / N;
    }

    // f2的改进版本，用到了观察位置优化枚举的技巧
    // N = 4 则f(4) = ( f(5) + 4 * f(5) - f(9) ) / 4
    // f(i) = ( f(i+1) + N * f(i+1) - f(i+N+1) ) / N
    public static double f3(int N, int a, int b) {
        if (N < 1 || a >= b || a < 0 || b < 0) {
            return 0.0;
        }
        // 赢的区间太长，不管怎么抽最后一张都能赢
        if (b - a >= N) {
            return 1.0;
        }
        return p3(N, 0, a, b);
    }

    public static double p3(int N, int cur, int a, int b) {
        if (cur >= b) {
            return 0.0;
        }
        if (cur >= a) {
            return 1.0;
        }
        // base case的前一个特殊
        if (cur == a - 1) {
            return 1.0 * (b - a) / N;
        }
        double ans = p3(N, cur + 1, a, b) + N * p3(N, cur + 1, a, b);
        if (cur + 1 + N < b) { // base case的前几个
            ans -= p3(N, cur + 1 + N, a, b);
        }
        return ans / N;
    }

    // f3的改进版本的动态规划
    // 可以课上讲一下
    public static double f4(int N, int a, int b) {
        if (N < 1 || a >= b || a < 0 || b < 0) {
            return 0.0;
        }
        if (b - a >= N) {
            return 1.0;
        }
        double[] dp = new double[b];
        for (int i = a; i < b; i++) {
            dp[i] = 1.0;
        }
        if (a - 1 >= 0) {
            dp[a - 1] = 1.0 * (b - a) / N;
        }
        for (int cur = a - 2; cur >= 0; cur--) {
            double w = dp[cur + 1] + dp[cur + 1] * N;
            if (cur + 1 + N < b) {
                w -= dp[cur + 1 + N];
            }
            dp[cur] = w / N;
        }
        return dp[0];
    }


    public static void main(String[] args) {
        int N = 10;
        int a = 17;
        int b = 21;
        System.out.println("N = " + N + ", a = " + a + ", b = " + b);
        System.out.println(f1());
        System.out.println(f2(N, a, b));
        System.out.println(f3(N, a, b));
        System.out.println(f4(N, a, b));

//        int maxN = 15;
//        int maxM = 20;
//        int testTime = 100000;
//        System.out.println("测试开始");
//        System.out.println("比对double类型答案可能会有精度对不准的问题");
//        System.out.println("所以答案一律只保留小数点后四位进行比对");
//        System.out.println("如果没有错误提示, 说明验证通过");
//        DecimalFormat df = new DecimalFormat("#.####");
//        for (int i = 0; i < testTime; i++) {
//            N = (int) (Math.random() * maxN);
//            a = (int) (Math.random() * maxM);
//            b = (int) (Math.random() * maxM);
//            double ans2 = Double.valueOf(df.format(f2(N, a, b)));
//            double ans3 = Double.valueOf(df.format(f3(N, a, b)));
//            double ans4 = Double.valueOf(df.format(f4(N, a, b)));
//            if (ans2 != ans3 || ans2 != ans4) {
//                System.out.println("Oops!");
//                System.out.println(N + " , " + a + " , " + b);
//                System.out.println(ans2);
//                System.out.println(ans3);
//                System.out.println(ans4);
//            }
//        }
//        System.out.println("测试结束");
//
//        N = 10000;
//        a = 67834;
//        b = 72315;
//        System.out.println("N = " + N + ", a = " + a + ", b = " + b + "时, 除了方法4外都超时");
//        System.out.print("方法4答案: ");
//        System.out.println(f4(N, a, b));
    }


}
