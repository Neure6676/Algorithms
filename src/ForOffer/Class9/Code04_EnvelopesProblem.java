package ForOffer.Class9;

import java.util.Arrays;
import java.util.Comparator;

// 本题测试链接 : https://leetcode.com/problems/russian-doll-envelopes/
public class Code04_EnvelopesProblem {

    // 排序：长度由小到大，长度一样时高度由大到小
    // 把高取出来做一个数组，它的最长递增SubString就是答案
    public static class Enveloper {
        public int l;
        public int h;

        public Enveloper(int[] e) {
            l = e[0];
            h = e[1];
        }
    }

    public static class EnveloperComparator implements Comparator<Enveloper> {
        @Override
        public int compare(Enveloper o1, Enveloper o2) {
            return o1.l == o2.l ? o2.h - o1.h : o1.l - o2.l;
        }
    }

    public int maxEnvelopes(int[][] e) {
        if (e == null || e.length == 0 || e[0].length == 0) {
            return 0;
        }
        int N = e.length;
        Enveloper[] es = new Enveloper[N];
        for (int i = 0; i < N; i++) {
            es[i] = new Enveloper(e[i]);
        }
        Arrays.sort(es, new EnveloperComparator());
        int[] end = new int[N];
        end[0] = es[0].h;
        int ans = 1;
        int right = 0; // end[]有效范围
        int l = 0;
        int r = right;
        int m = 0;
        for (int i = 1; i < N; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (r + l) / 2;
                if (es[i].h > end[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            end[l] = es[i].h;
            ans = Math.max(ans, l + 1);
        }
        return ans;
    }

}
