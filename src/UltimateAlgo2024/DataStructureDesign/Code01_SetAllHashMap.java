package UltimateAlgo2024.DataStructureDesign;

import java.io.*;
import java.util.HashMap;

// https://www.nowcoder.com/practice/7c4559f138e74ceb9ba57d76fd169967
public class Code01_SetAllHashMap {

    public static HashMap<Integer, int[]> map = new HashMap<>();
    public static int setAllValue;
    public static int setAllTime;
    // Timestamp
    public static int cnt;

    public static void put(int k, int v) {
        if (map.containsKey(k)) {
            int[] val = map.get(k);
            val[0] = v;
            val[1] = cnt++;
        } else {
            map.put(k, new int[] {v, cnt++});
        }
    }

    public static void setAll(int v) {
        setAllValue = v;
        setAllTime = cnt++;
    }

    public static int get(int k) {
        if (!map.containsKey(k)) {
            return -1;
        }
        int[] val = map.get(k);
        if (val[1] > setAllTime) {
            return val[0];
        } else {
            return setAllValue;
        }
    }

    public static int n, op, a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            map.clear();
            setAllValue = 0;
            setAllTime = -1;
            cnt = 0;
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                op = (int) in.nval;
                if (op == 1) {
                    in.nextToken();
                    a = (int) in.nval;
                    in.nextToken();
                    b = (int) in.nval;
                    put(a, b);
                } else if (op == 2) {
                    in.nextToken();
                    a = (int) in.nval;
                    out.println(get(a));
                } else {
                    in.nextToken();
                    a = (int) in.nval;
                    setAll(a);
                }
            }
        }
        out.flush();;
        out.close();
        br.close();
    }
}
