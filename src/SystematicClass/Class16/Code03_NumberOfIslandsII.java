package SystematicClass.Class16;

import java.util.ArrayList;
import java.util.List;

// 本题为leetcode原题
// 测试链接：https://leetcode.com/problems/number-of-islands-ii/
// 所有方法都可以直接通过
public class Code03_NumberOfIslandsII {

    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        UnionFind uf = new UnionFind(m, n);
        List<Integer> ans = new ArrayList<>();    // ArrayList
        for (int[] position : positions) {        // [[a,b], [c,d], ...]
            ans.add(uf.connect(position[0], position[1]));
        }
        return ans;
    }

    public static class UnionFind {
        private int[] parent;
        private int[] size; // 若size[1]不为0，则i已经被初始化过
        private int[] help;
        private final int row;
        private final int col;
        private int sets;

        public UnionFind(int m ,int n) {
            row = m;
            col = n;
            sets = 0;
            int len = row * col;
            parent = new int[len];
            size = new int[len];  // 如果size[i]不等于0，说明i位置被初始化过
            help = new int[len];
        }

        private int index(int r, int c) {
            return r * col + c;
        }

        private int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        private void union(int r1, int c1, int r2, int c2) {
            if (r1 < 0 || r1 == row || r2 < 0 || r2 == row || c1 < 0 || c1 == col || c2 < 0 || c2 == col) {
                return;
            }
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            if (size[i1] == 0 || size[i2] == 0) { // 需同时为1才连接
                return;
            }
            int f1 = find(i1);
            int f2 = find(i2);
            if (f1 != f2) {
                if (size[f1] < size[f2]) {
                    parent[f1] = f2;
                    size[f2] += size[f1];
                } else {
                    parent[f2] = f1;
                    size[f1] += size[f2];
                }
                sets--;
            }
        }


        public int connect(int r, int c) {  // r行c列上来了个1
            int index = index(r, c);
            if (size[index] == 0) { //这个位置没有空降过1
                parent[index] = index;
                size[index] = 1;
                sets++;
                union(r, c, r - 1, c);
                union(r, c, r + 1, c);
                union(r, c, r, c + 1);
                union(r, c, r, c - 1);
            }
            return sets;
        }
    }
}
