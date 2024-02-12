package UltimateAlgo2024.DynamicProgramming.OneD;

// https://leetcode.com/problems/distinct-subsequences-ii/
public class Code08_DistinctSubsequencesII {

    public int distinctSubseqII(String str) {
        int mod = 1000000007;
        char[] s = str.toCharArray();
        int[] cnt = new int[26];
        int all = 1, newAdd;
        for (char cur : s) {
            newAdd = (all - cnt[cur - 'a'] + mod) % mod;
            all = (all + newAdd) % mod;
            cnt[cur - 'a'] = (cnt[cur - 'a'] + newAdd) % mod;
        }
        return (all - 1 + mod) % mod;
    }

}
