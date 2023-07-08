package SystematicClass.Class20;

/**
 * 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 */
public class Code02_ConvertToLetterString {

    // str只含有数字0-9，返回共有多少种转化方案
    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    // 表示用i出发到最后有几种转化方法
    public static int process(char[] str, int i) {
        if (i == str.length) {
            return 1; // 直接把下游定好的那种方法返回
        }
        // i没到最后
        if (str[i] == '0') {  // 如果剩下一个0，说明有问题，因该连着前一个一起转化
            return 0;
        }
        // i不是0
        // 可能性1，单转
        int ways = process(str, i+1);
        // 可能性2，i，i+1共同转
        if (i + 1 < str.length && (str[i] - '0') * 10 + str[i+1] - '0' < 27) { //nb
            ways += process(str, i+2);
        }
        return ways;
    }






    // *从右往左*的动态规划
    // 就是上面方法的动态规划版本
    // dp[i]表示：str[i...]有多少种转化方式
    public static int dp1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1]; // N位置其实是越界的，0～N-1上有数
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (str[i] != '0') {
                int ways = dp[i + 1];
                if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
                    ways += dp[i + 2];
                }
                dp[i] = ways;
            }
        }
        return dp[0];
    }

    // 为了测试
    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }

    // 为了测试
    public static void main(String[] args) {
        int N = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            String s = randomString(len);
            int ans0 = number(s);
            int ans1 = dp1(s);
            if (ans0 != ans1) {
                System.out.println(s);
                System.out.println(ans0);
                System.out.println(ans1);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
