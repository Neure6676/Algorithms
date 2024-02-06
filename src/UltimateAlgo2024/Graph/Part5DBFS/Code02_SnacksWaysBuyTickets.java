package UltimateAlgo2024.Graph.Part5DBFS;

import java.io.*;
import java.util.Arrays;

// 测试链接 : https://www.nowcoder.com/practice/d94bb2fa461d42bcb4c0f2b94f5d4281
// 测试链接 : https://www.luogu.com.cn/problem/P4799
public class Code02_SnacksWaysBuyTickets {

    public static int MAXN = 40;

    public static int MAXM = 1 << 20;

    public static long[] arr = new long[MAXM];

    public static long[] lsum = new long[MAXM];

    public static long[] rsum = new long[MAXM];

    public static int n;

    public static long w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            w = (long) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i] = (long) in.nval;
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static long compute() {
        int lsize = f(0, n >> 1, 0, w, lsum, 0);
        int rsize = f(n >> 1, n, 0, w, rsum, 0);
        Arrays.sort(lsum, 0, lsize);
        Arrays.sort(rsum, 0, rsize);
        long ans = 0;
        for (int i = 0, j = rsize - 1; i < lsize; i++) {
            while (j >= 0 && lsum[i] + rsum[j] > w) {
                j--;
            }
            ans += j + 1;
        }
        return ans;
    }

    // arr[i....e]结束，e再往右不展开了！
    // s: 当前累加和， w：背包容量，j：该填到什么位置
    // 返回值 : ans数组最后填到了什么位置！
    public static int f(int i, int e, long s, long w, long[] ans, int j) {
        if (s > w) {
            return j;
        }
        // s <= w
        if (i == e) {
            ans[j++] = s;
        } else {
            // 不要arr[i]位置的数
            j = f(i + 1, e, s, w, ans, j);
            // 要arr[i]位置的数
            j = f(i + 1, e, s + arr[i], w, ans, j);
        }
        return j;
    }
}
