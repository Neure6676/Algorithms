package SystematicClass.Class22;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 */
public class Code02_CoinsWayEveryPaperDifferent {
    public static int coinWays(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    // 返回从arr[index ..]能组成rest这么多钱的方法数
    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (index == arr.length) { // 已经越界. 记得要考虑越界
            return rest == 0 ? 1 : 0;
        } else {
            // 要或不要
            int yes = process(arr, index + 1, rest - arr[index]);
            int no = process(arr, index + 1, rest);
            return yes + no;
        }
    }

    //dp
    public static int dp(int[] arr, int aim) {
        if (aim == 0) {
            return 1;  // 这唯一一种方法是：都不要
        }
        int N = arr.length;
        // 行数必须是N+1，下标为N的对应base case，那是终止位置，已经越界
        int[][] dp = new int[N + 1][aim + 1];    // 不要忘记new啊！！！
        dp[N][0] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j] = dp[i + 1][j] + (j - arr[i] >= 0 ? dp[i + 1][j - arr[i]] : 0);
            }
        }
        return dp[0][aim];
    }


    public static int dp1(int[] arr, int aim) {
        if (aim == 0) {
            return 1;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest] + (rest - arr[index] >= 0 ? dp[index + 1][rest - arr[index]] : 0);
            }
        }
        return dp[0][aim];
    }




    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinWays(arr, aim);
            int ans2 = dp(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
