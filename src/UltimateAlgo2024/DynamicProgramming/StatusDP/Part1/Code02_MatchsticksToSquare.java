package UltimateAlgo2024.DynamicProgramming.StatusDP.Part1;

// https://leetcode.com/problems/matchsticks-to-square/
public class Code02_MatchsticksToSquare {

    public static boolean makesquare(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 4 != 0) {
            return false;
        }
        int n = nums.length;
        int[] dp = new int[1 << n];
        return f(nums, sum / 4, (1 << n) - 1, 0, 4, dp);
    }

    // limit : 每条边规定的长度
    // status : 表示哪些数字还可以选
    // cur : 当前要解决的这条边已经形成的长度
    // rest : 一共还有几条边没有解决
    // 返回 : 能否用光所有火柴去解决剩下的所有边
    // 因为调用子过程之前，一定保证每条边累加起来都不超过limit
    // 所以status是决定cur和rest的，关键可变参数只有status
    public static boolean f(int[] nums, int limit, int status, int cur, int rest, int[] dp) {
        if (rest == 0) {
            return status == 0;
        }
        if (dp[status] != 0) {
            return dp[status] == 1;
        }
        boolean ans = false;
        for (int i = 0; i < nums.length; i++) {
            // 考察每一根火柴，只能使用状态为1的火柴
            if ((status & (1 << i)) != 0 && cur + nums[i] <= limit) {
                if (cur + nums[i] == limit) {
                    ans = f(nums, limit, status ^ (1 << i), 0, rest - 1, dp);
                } else {
                    ans = f(nums, limit, status ^ (1 << i), cur + nums[i], rest, dp);
                }
                if (ans) {
                    break;
                }
            }
        }
        dp[status] = ans ? 1 : -1;
        return ans;
    }
}
