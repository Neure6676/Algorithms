package ForOffer.Class27;

// https://leetcode.com/problems/reverse-integer/
public class Problem_0007_ReverseInteger {

    public int reverse(int x) {
        int num = Math.abs(x);
        int ans = 0;
        while (num != 0) {
            int remains = num % 10;
            // Since we are updating rev in every while loop, it would be wise to check beforehand if the value goes beyond the upper limit or not.
            // So before we do ans = ans * 10 + remains, we check if ans * 10 + remains > MAX_VALUE
            if (ans > (Integer.MAX_VALUE - remains) / 10) {
                return 0;
            }
            ans = ans * 10 + remains;
            num = num / 10;
        }
        return x >= 0 ? ans : -ans;
    }
}
