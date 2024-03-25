package UltimateAlgo2024.DynamicProgramming.DigitDP;

// https://leetcode.com/problems/count-numbers-with-unique-digits/
public class Code01_CountNumbersWithUniqueDigits {

    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        // 1 : 10
        // 2 : 9 * 9
        // 3 : 9 * 9 * 8
        // 4 : 9 * 9 * 8 * 7
        // ...都累加起来...
        int ans = 10;
        for (int s = 9, i = 9, k = 2; k <= n; i--, k++) {
            s *= i;
            ans += s;
        }
        return ans;
    }
}
