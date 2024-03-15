package UltimateAlgo2024.KMP;

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
        int n = s1.length, m = s2.length, i = 0, j = 0;
        int[] next = nextArray(s2, m);
        while (i < n && j < m) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = next[j];
            }
        }
        return j == m ? i - j : -1;
    }

    public static int[] nextArray(char[] s, int m) {
        if (m == 1) {
            return new int[] { -1 };
        }
        int[] next = new int[m];
        next[0] = -1;
        next[1] = 0;
        // cn表示当前要和i位置前一个字符比对的下标
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
