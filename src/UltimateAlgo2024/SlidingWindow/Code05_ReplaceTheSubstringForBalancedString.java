package UltimateAlgo2024.SlidingWindow;

// https://leetcode.com/problems/replace-the-substring-for-balanced-string/
public class Code05_ReplaceTheSubstringForBalancedString {

    public int balancedString(String s) {
        int n = s.length();
        int[] arr = new int[n];
        // cnt是窗口之外的统计
        int[] cnt = new int[4];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            arr[i] = c == 'W' ? 1 : (c == 'E' ? 2 : (c == 'R' ? 3 : 0));
            cnt[arr[i]]++;
        }
        int required = n / 4;
        int ans = n;
        for (int l = 0, r = 0; l < n; l++) {
            // l = 0, r= 0, 窗口0长度
            // l...r-1 : [l,r)
            while ((!ok(cnt, r - l, required)) && r < n) {
                cnt[arr[r++]]--;
            }
            if (ok(cnt, r - l, required)) {
                ans = Math.min(ans, r - l);
            }
            cnt[arr[l]]++;
        }
        return ans;
    }

    // cnts : l...r范围上的字符不算！在自由变化的窗口之外，每一种字符的词频统计
    // len : 自由变化窗口的长度
    // require : 每一种字符都要达到的数量
    // 返回值 : 请问能不能做到
    public static boolean ok(int[] cnt, int len, int require) {
        for (int i = 0; i < 4; i++) {
            if (cnt[i] > require) {
                return false;
            }
            len -= require - cnt[i];
        }
        return len == 0;
    }
}
