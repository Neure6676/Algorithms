package UltimateAlgo2024.KMP.Part1;

// https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
public class Code01_KMP {

    public int strStr1(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        char[] hay = haystack.toCharArray();
        char[] nee = needle.toCharArray();
        for (int i = 0; i < n; i++) {
            if (hay[i] == nee[0]) {
                int tar = m - 1, j = 0, curI = i;
                while (curI + 1 < n && j + 1 < m && tar != 0) {
                    if (hay[++curI] != nee[++j]) {
                        break;
                    }
                    tar--;
                }
                if (tar == 0) {
                    return i;
                }
            }
        }
        return -1;
    }



    public int strStr2(String str1, String str2) {
        return KMP(str1.toCharArray(), str2.toCharArray());
    }

    public static int KMP(char[] s1, char[] s2) {
        // s1中当前比对的位置是x
        // s2中当前比对的位置是y
        int n = s1.length, m = s2.length, x = 0, y = 0;
        int[] next = nextArray(s2, m);
        while (x < n && y < m) {
            if (s1[x] == s2[y]) {
                x++;
                y++;
            } else if (y == 0) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == m ? x - y : -1;
    }

    public static int[] nextArray(char[] s, int m) {
        if (m == 1) {
            return new int[] { -1 };
        }
        int[] next = new int[m];
        next[0] = -1;
        next[1] = 0;
        // i表示当前要求next值的位置
        // cn表示当前要和前一个字符比对的下标
        int i = 2, cn = 0;
        while (i < m) {
            if (s[i - 1] == s[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
}
