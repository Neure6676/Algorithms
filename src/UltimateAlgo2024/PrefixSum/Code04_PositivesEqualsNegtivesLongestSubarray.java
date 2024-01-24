package UltimateAlgo2024.PrefixSum;

import java.io.*;
import java.util.HashMap;

//https://www.nowcoder.com/practice/545544c060804eceaed0bb84fcd992fb
public class Code04_PositivesEqualsNegtivesLongestSubarray {
    public static int MAXN = 100001;

    public static int[] arr = new int[MAXN];

    public static int n;

    public static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for (int i = 0, num; i < n; i++) {
                in.nextToken();
                num = (int) in.nval;
                arr[i] = num != 0 ? (num > 0 ? 1 : -1) : 0;
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute() {
        map.clear();
        map.put(0, -1);
        int ans = 0;
        for (int i = 0, sum = 0; i < n; i++) {
            sum += arr[i];
            if (map.containsKey(sum)) {
                ans = Math.max(ans, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return ans;
    }
}
