package UltimateAlgo2024.StringHash.Part1;

import java.io.*;
import java.util.Arrays;

// https://www.luogu.com.cn/problem/P3370
public class Code01_DifferentStrings {

    public static int MAXN = 10001;

    public static int base = 499;

    public static long[] nums = new long[MAXN];

    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(in.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = value(in.readLine().toCharArray());
        }
        out.println(cnt());
        out.flush();
        out.close();
        in.close();
    }

    public static long value(char[] s) {
        long ans = v(s[0]);
        for (int i = 1; i < s.length; i++) {
            ans = ans * base + v(s[i]);
        }
        return ans;
    }

    // 数字 + 大写 + 小写
    // '0' -> 1
    // '1' -> 2
    // ...
    // '9' -> 10
    // 'A' -> 11
    // 'B' -> 12
    // ...
    // 'Z' -> 36
    // 'a' -> 37
    // ...
    // 'z' -> 62
    public static int v(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0' + 1;
        } else if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 11;
        } else {
            return c - 'a' + 37;
        }
    }

    public static int cnt() {
        Arrays.sort(nums, 0, n);
        int ans = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                ans++;
            }
        }
        return ans;
    }

}
