package ForOffer.OA;

public class MaxChocolates {

    public static int maxChocolate(int[] jars) {
        if (jars == null || jars.length == 0) {
            return 0;
        }
        int N = jars.length;
        // dp[i]位置必拿
        int[] dp = new int[N];
        dp[0] = jars[0];
        dp[1] = Math.max(jars[0], jars[1]);
        int max = jars[0];
        for (int i = 2; i < N; i++) {
            dp[i] = Math.max(dp[i - 2] + jars[i], dp[i - 1]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    public static void main(String[] args) {
        int[] jars = {5, 30, 99, 60, 5, 10};
        System.out.println(maxChocolate(jars));
    }
}
