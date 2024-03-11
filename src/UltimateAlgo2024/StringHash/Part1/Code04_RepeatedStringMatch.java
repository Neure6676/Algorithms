package UltimateAlgo2024.StringHash.Part1;

// https://leetcode.com/problems/repeated-string-match/
public class Code04_RepeatedStringMatch {

    public static int MAXN = 30001;

    public static int[] s = new int[MAXN];

    public static int base = 499;

    public static long[] pow = new long[MAXN];

    public static long[] hash = new long[MAXN];

    public int repeatedStringMatch(String a, String b) {
        char[] s1 = a.toCharArray();
        char[] s2 = b.toCharArray();
        int n = s1.length;
        int m = s2.length;
        int k = (m + n - 1) / n;
        int len = 0;
        for (int cnt = 0; cnt <= k; cnt++) {
            for (int i = 0; i < n; i++) {
                s[len++] = s1[i];
            }
        }
        build(len);
        long hashOfS2 = s2[0] - 'a' + 1;
        for (int i = 1; i < m; i++) {
            hashOfS2 = hashOfS2 * base + s2[i] - 'a' + 1;
        }
        for (int l = 0, r = m - 1; r < len; l++, r++) {
            if (hash(l, r) == hashOfS2) {
                return r < n * k ? k : (k + 1);
            }
        }
        return -1;
    }

    public static void build(int n) {
        pow[0] = 1;
        for (int i = 1; i < n; i++) {
            pow[i] = pow[i - 1] * base;
        }
        hash[0] = s[0] - 'a' + 1;
        for (int i = 1; i < n; i++) {
            hash[i] = hash[i - 1] * base + s[i] - 'a' + 1;
        }
    }

    public static long hash(int l, int r) {
        long ans = hash[r];
        ans -= l == 0 ? 0 : (hash[l - 1] * pow[r - l + 1]);
        return ans;
    }
}
