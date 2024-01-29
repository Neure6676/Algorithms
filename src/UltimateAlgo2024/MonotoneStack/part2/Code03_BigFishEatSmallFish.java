package UltimateAlgo2024.MonotoneStack.part2;

import java.io.*;

// https://www.nowcoder.com/practice/77199defc4b74b24b8ebf6244e1793de
public class Code03_BigFishEatSmallFish {

    public static int MAX = 100001;

    public static int[] arr = new int[MAX];

    public static int[][] stack = new int[MAX][2];

    public static int n, r;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
            }
            out.println(turns());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int turns() {
        r = 0;
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            int cur = 0;
            while (r > 0 && arr[i] > arr[stack[r - 1][0]]) {
                cur = Math.max(cur + 1, stack[--r][1]);
            }
            stack[r][0] = i;
            stack[r++][1] = cur;
            ans = Math.max(ans, cur);
        }
        return ans;
    }
}
