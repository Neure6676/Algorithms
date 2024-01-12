package UltimateAlgo2024.Heap;

import java.io.*;
import java.util.Arrays;

// https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
// https://www.nowcoder.com/practice/1ae8d0b6bb4e4bcdbf64ec491f63fc37

// 线段最大重合问题
public class Code02_MaxCover {

    public static int MAXN = 10001;

    public static int[][] lines = new int[MAXN][2];

    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                lines[i][0] = (int) in.nval;
                in.nextToken();
                lines[i][1] = (int) in.nval;
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    // 小根堆，堆顶0位置
    public static int[] heap = new int[MAXN];

    // 堆的大小
    public static int size;

    public static int compute() {
        // clean up the stack
        size = 0;

        Arrays.sort(lines, 0, n, (a, b) -> a[0] - b[0]);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            while (size > 0 && heap[0] <= lines[i][0]) {
                pop();
            }
            add(lines[i][1]);
            ans = Math.max(ans, size);
        }
        return ans;
    }

    public static void add(int cur) {
        heap[size] = cur;
        int i = size++;
        while(heap[i] < heap[(i - 1) / 2]) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    public static void pop() {
        swap(0, --size);
        int i = 0;
        int left = 1;
        while (left < size) {
            int best = left + 1 < size && heap[left] > heap[left + 1] ? left + 1 : left;
            best = heap[best] < heap[i] ? best : i;
            if (best == i) {
                break;
            }
            swap(best, i);
            i = best;
            left = 2 * i + 1;
        }
    }

    public static void swap(int a, int b) {
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }
}
