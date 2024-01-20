package UltimateAlgo2024.Recursion.PermutationAndCombination;

import java.util.HashSet;

// https://www.nowcoder.com/practice/92e6247998294f2c933906fdedbc6e6a
public class Code01_Subsequences {

    public static String[] generatePermutation1(String str) {
        String[] ans = new String[(int) Math.pow(2, str.length())];
        char[] s = str.toCharArray();
        HashSet<String> set = new HashSet<>();
        int i = 0;
        for (String cur : set) {
            ans[i++] = cur;
        }
        return ans;
    }

    public static void process(char[] s, int i, StringBuilder sb, HashSet<String> set) {
        if (i == s.length) {
            set.add(sb.toString());
        } else {
            sb.append(s[i]);
            process(s, i + 1, sb, set);
            sb.deleteCharAt(sb.length() - 1);
            process(s, i + 1, sb, set);
        }
    }
}
