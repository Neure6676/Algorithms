package UltimateAlgo2024.Recursion.NestProblems;

// https://leetcode.com/problems/decode-string/
public class Code02_DecodeString {

    public static int where;

    public String decodeString(String s) {
        where = 0;
        return f(s.toCharArray(), where);
    }

    String f(char[] s, int i) {
        StringBuilder path = new StringBuilder();
        int cur = 0;
        while (i < s.length && s[i] != ']') {
            if (s[i] >= '0' && s[i] <= '9') {
                cur = cur * 10 + s[i++] - '0';
            } else if ((s[i] >= 'a' && s[i] <= 'z') || (s[i] >= 'A' && s[i] <= 'Z')){
                path.append(s[i++]);
            } else {
                path.append(get(cur, f(s, i + 1)));
                i = where + 1;
                cur = 0;
            }
        }
        where = i;
        return path.toString();
    }

    String get(int cur, String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cur; i++) {
            sb.append(s);
        }
        return sb.toString();
    }
}
