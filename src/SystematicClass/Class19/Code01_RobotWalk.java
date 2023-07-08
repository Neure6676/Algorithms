package SystematicClass.Class19;

/**
 * 假设有排成一行的N个位置记为1~N，N一定大于或等于2
 * 开始时机器人在其中的M位置上(M一定是1~N中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到N-1位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走K步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回共有几种方法
 */

public class Code01_RobotWalk {
    // 先想怎么把尝试写出来，即暴力递归
    public static int way1(int N, int start, int aim, int K) {
        return process(start, K, aim, N);
    }

    // 机器人当前来到的位置是cur，
    // 机器人还有rest步需要去走，
    // 最终的目标是aim，
    // 有哪些位置？1~N
    // 返回：机器人从cur出发，走过rest步之后，最终停在aim的方法数，是多少？
    public static int process(int cur, int rest, int aim, int N) {
        if (rest == 0) { // base case
            return cur == aim ? 1 : 0;  // 此时如果不在aim上，就不算一种方法
        }
        // 还有步数要走
        if (cur == 1) {
            return process(2, rest - 1, aim, N); // 若从1到N要8步，从2到N必有7步
        }
        if (cur == N) {
            return process(N - 1, rest - 1, aim, N); // 若从1到N要8步，从2到N必有7步
        }
        // 停在中间位置上
        // 从6经过8步到aim的方法数 = 从5经过7步到aim的方法数 + 从7经过7步到aim的方法数
        return process(cur - 1, rest - 1, aim, N) + process(cur + 1, rest - 1, aim, N);
    }





    // 动态规划：去掉所有重复过程
    // 笨办法，从顶向下的动态规划(记忆化搜索)
    public static int way2(int N, int start, int aim, int K) {
        int[][] dp = new int[N+1][K+1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j<= N; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(start, K, aim, N, dp);
    }

    public static int process2(int cur, int rest, int aim, int N, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        int ans = 0;
        if (rest == 0) {   // base case
            ans = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            ans = process2(2, rest - 1, aim, N, dp);
        } else if (cur == N) {
            ans = process2(N - 1, rest - 1, aim, N, dp);
        } else {
            ans = process2(cur - 1, rest - 1, aim, N, dp) + process2(cur + 1, rest - 1, aim, N, dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }




    // *动态规划 最终版本*
    public static int way3(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        int[][] dp = new int[N+1][K+1];
        dp[aim][0] = 1; // 第0列剩下的都是0（java默认）

        //dp
        //col: rest; row: cur
        for (int rest = 1; rest <= K; rest++) { //col
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < N; cur++) { //only 2~(N-1)rol
               dp[cur][rest] =  dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[N][rest] = dp[N - 1][rest - 1];
        }

        return dp[start][K];
    }




    public static void main(String[] args) {
        System.out.println(way1(4, 2, 4, 4));
        System.out.println(way2(4, 2, 4, 4));
        System.out.println(way3(4, 2, 4, 4));
    }
}
