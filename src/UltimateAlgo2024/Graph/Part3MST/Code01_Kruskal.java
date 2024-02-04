package UltimateAlgo2024.Graph.Part3MST;

import java.io.*;
import java.util.Arrays;

// https://www.luogu.com.cn/problem/P3366
public class Code01_Kruskal {

    public static int MAXV = 5001;

    public static int MAXE = 200001;

    public static int[] father = new int[MAXV];

    public static int[][] edges = new int[MAXE][3];

    public static int v, e;

    // 并查集build
    public static void build() {
        for (int i = 1; i <= v; i++) {
            father[i] = i;
        }
    }

    // 并查集find
    public static int find(int i) {
        if (i != father[i]) {
            father[i] = find(father[i]);
        }
        return father[i];
    }

    // 如果x和y本来就是一个集合 -> 环，返回false
    // 如果x和y不是一个集合，合并之后返回true
    public static boolean union(int i, int j) {
        int fi = find(i);
        int fj = find(j);
        if (fi == fj) {
            father[fi] = fj;
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            v = (int) in.nval;
            in.nextToken();
            e = (int) in.nval;
            build();
            for (int i = 0; i < e; i++) {
                for (int j = 0; j < 3; j++) {
                    in.nextToken();
                    edges[i][j] = (int) in.nval;
                }
            }
            Arrays.sort(edges, 0, e, (a, b) -> a[2] - b[2]);
            int ans = 0;
            int cntEdges = 0;
            for (int[] edge : edges) {
                // 边的左右两侧是不是一个集合
                if (union(edge[0], edge[1])) {
                    ans += edge[2];
                    cntEdges++;
                }
            }
            out.println(cntEdges == v - 1 ? ans : "orz");
        }
        out.flush();
        out.close();
        br.close();
    }
}
