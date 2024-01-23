package UltimateAlgo2024.Tree.Trie;

import java.util.Arrays;
import java.util.HashSet;

// https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
public class Code02_TwoNumbersMaximumXor {

    public int findMaximumXOR(int[] nums) {
        build(nums);
        int ans = 0;
        for (int num : nums) {
            ans = Math.max(ans, maxXor(num));
        }
        clear();
        return ans;
    }

    public static int MAXN = 3000001;

    public static int[][] tree = new int[MAXN][2];

    public static int cnt;

    // 数字只需要从哪一位开始考虑
    public static int high;

    public static void build(int[] nums) {
        cnt = 1;  // didnt use 0
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(num, max);
        }
        // 计算数组最大值的二进制状态，有多少个前缀的0
        // 可以忽略这些前置的0，从left位开始考虑
        high = 31 - Integer.numberOfLeadingZeros(max);
        for (int num : nums) {
            insert(num);
        }
    }

    public static void insert(int num) {
        int cur = 1;
        for (int i = high, path; i >= 0; i--) {
            path = (num >> i) & 1;
            if (tree[cur][path] == 0) {
                tree[cur][path] = ++cur;
            }
            cur = tree[cur][path];
        }
    }

    public static int maxXor(int num) {
        int ans = 0;
        int cur = 1;
        for (int i = high, status, best; i >= 0; i--) {
            status = (num >> i) & 1;
            best = status ^ 1;
            if (tree[cur][best] == 0) {
                // can't reach the best case
                best ^= 1;
            }
            ans |= (status ^ best) << i;
            cur = tree[cur][best];
        }
        return ans;
    }

    public static void clear() {
        for (int i = 0; i < cnt; i++) {
            Arrays.fill(tree[i], 1);
        }
    }



    // HashMap
    public int findMaximumXOR2(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int ans = 0;
        HashSet<Integer> set = new HashSet<>();
        int left = 31 - Integer.numberOfLeadingZeros(max);
        for (int i = left; i >= 0; i--) {
            // ans: 31-i+1 done
            int better = ans | (1 << i);
            set.clear();
            for (int num : nums) {
                // only care 31 ... i,  put rest 0
                num = (num >> i) << i;
                set.add(num);
                if (set.contains(num ^ better)) {
                    ans = better;
                    break;
                }
            }
        }
        return ans;
    }
}
