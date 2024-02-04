package UltimateAlgo2024.Graph.Part3MST;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

// https://www.luogu.com.cn/problem/P3366
public class Code02_Prim {

    public static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

    public static int v, e;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            v = (int) in.nval;
            in.nextToken();
            e = (int) in.nval;
            for (int i = 0; i < e; i++) {
                in.nextToken();
                int from = (int) in.nval;
                in.nextToken();
                int to = (int) in.nval;
                in.nextToken();
                int weight = (int) in.nval;
                graph.get(from).add(new int[] {to, weight});
                graph.get(to).add(new int[] {from, weight});
            }
            // int[] record
            // record[0] : 到达的节点
            // record[1] : 到达的花费
            PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            for (int[] edge : graph.get(1)) {
                heap.add(edge);
            }
            // 哪些节点已经发现过了
            boolean[] set = new boolean[v + 1];
            int nodeCnt = 1;
            set[1] = true;
            int ans = 0;
            while (!heap.isEmpty()) {
                int[] edge = heap.poll();
                int to = edge[0];
                int weight = edge[1];
                if (!set[to]) {
                    nodeCnt++;
                    set[to] = true;
                    ans += weight;
                    for (int[] cur : graph.get(to)) {
                        heap.add(cur);
                    }
                }
            }
            out.println(nodeCnt == v - 1 ? ans : "orz");
        }
        out.flush();
        out.close();
        br.close();
    }
}
