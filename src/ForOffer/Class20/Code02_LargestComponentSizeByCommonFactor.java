package ForOffer.Class20;

import java.util.HashMap;

/**
 * 测试链接：https://leetcode.com/problems/largest-component-size-by-common-factor/
 * Union Find 并查集
 * 分别看每个数有哪些因子，把有相同因子的放到一个集合 O(n * sqrt(v))
 */
public class Code02_LargestComponentSizeByCommonFactor {

    public static int largestComponentSize(int[] arr) {
        int N  = arr.length;
        // 初始化时每个arr中的位置自己是一个集合
        UnionFind unionFind = new UnionFind(N);
        // key：某个因子  val：哪个位置拥有这个因子
        HashMap<Integer, Integer> factorMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int num = arr[i];
            // limit = sqrt(N)
            int limit = (int)Math.sqrt(num);
            for (int j = 1; j <= limit; j++) {
                if (num % j == 0) {
                    if (j != 1) {
                        if (!factorMap.containsKey(j)) {
                            factorMap.put(j, i);
                        } else {
                            unionFind.union(factorMap.get(j), i);
                        }
                    }
                    int other = num / j;
                    if (other != 1) {
                        if (!factorMap.containsKey(other)) {
                            factorMap.put(other, i);
                        } else {
                            unionFind.union(factorMap.get(other), i);
                        }
                    }
                }
            }
        }
        return unionFind.maxSize();
    }


    // m, n的最大公约数 辗转相除法Euclidean algorithm
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // 1.扁平化 2.小挂大
    public static class UnionFind {
        private int[] parents;
        private int[] sizes;
        private int[] help;

        public UnionFind(int N) {
            parents = new int[N];
            sizes = new int[N];
            help = new int[N];
            for (int i = 0; i < N; i++) {
                parents[i] = i;
                sizes[i] = 1;
            }
        }

        public int maxSize() {
            int ans = 0;
            for (int size : sizes) {
                ans = Math.max(ans, size);
            }
            return ans;
        }

        private int find(int i) {
            int hi = 0; // 这条路上有几个
            while (i != parents[i]) {
                help[hi++] = i;  // 这条路上有谁
                i = parents[i];
            }
            // 扁平化优化
            for (hi--; hi >= 0; hi--) {
                parents[help[hi]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            int f1 = find(i);
            int f2 = find(j);
            if (f1 != f2) {
                int larger = sizes[f1] >= sizes[f2] ? f1 : f2;
                int smaller = larger == f1 ? f2 : f1;
                parents[smaller] = larger;
                sizes[larger] = sizes[f1] + sizes[f2];
            }
        }
    }

}
