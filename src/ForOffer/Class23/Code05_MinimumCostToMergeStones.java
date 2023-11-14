package ForOffer.Class23;

// https://leetcode.com/problems/minimum-cost-to-merge-stones/
public class Code05_MinimumCostToMergeStones {

//	// arr[L...R]一定要整出P份，合并的最小代价，返回！ f(L,R,P)
//	public static int f(int[] arr, int K, int L, int R, int P) {
//		if(从L到R根本不可能弄出P份) {
//			return Integer.MAX_VALUE;
//		}
//		// 有可能
//		if(P == 1) {
//			return L == R ? 0 : (f(arr, K, L, R, K) + 最后一次大合并的代价);
//		}
//		int ans = Integer.MAX_VALUE;
//		// 真的有可能，P > 1
//		for(int i = L; i < R;i++) {
//			// L..i(1份)  i+1...R(P-1份)
//			int left = f(arr, K, L, i, 1);
//			if(left == Integer.MAX_VALUE) {
//				continue;
//			}
//			int right = f(arr, K, i+1,R,P-1);
//			if(right == Integer.MAX_VALUE) {
//				continue;
//			}
//			int all = left + right;
//			ans = Math.min(ans, all);
//		}
//		return ans;
//	}

    public int mergeStones(int[] stones, int K) {
        int n = stones.length;
        if ((n - 1) % (K - 1) > 0) { // n个数能不能K个相邻合并， 成一个数
            return -1;
        }
        int[] presum = new int[n+1];
        for (int i = 0; i < n; i++) {
            presum[i+1] = presum[i] + stones[i];
        }
        int[][][] dp = new int[n][n][K + 1];
        return process(0, n - 1, 1, stones, K, presum, dp);
    }


    // 前缀和presum用来计算最后一次合并大代价
    public static int process0(int L, int R, int[] arr, int P, int K, int[] presum) {
        if (L == R) {
            return P == 1 ? 0 : -1;
        }
        // L...R不止一个数
        if (P == 1) {
            int next = process0(L, R, arr, K, K, presum);
            if (next == -1) {
                return -1;
            } else {
                return next + presum[R + 1] - presum[L];
            }
        } else { // P > 1
            int ans = Integer.MAX_VALUE;
            // L...i是第一块，剩下是另外P-1块
            for (int i  = L; i < R; i++) {
                int left = process0(L, i, arr, 1, K, presum);
                int right = process0(i + 1, R, arr, P - 1, K, presum);
                if (left != -1 && right != -1) {
                    ans = Math.min(ans, left + right);
                }
            }
            return ans;
        }
    }


    public static int process(int L, int R, int P, int[] arr, int K, int[] presum, int[][][] dp) {
        if (dp[L][R][P] != 0) {
            return dp[L][R][P];
        }
        if (L == R) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        if (P == 1) {
            ans = process(L, R, K, arr, K, presum, dp) + presum[R + 1] - presum[L];
        } else {
            for (int mid = L; mid < R; mid += K - 1) { // 省去有效性检查 保证一定有效
                int next1 = process(L, mid, 1, arr, K, presum, dp);
                int next2 = process(mid + 1, R, P - 1, arr, K, presum, dp);
                ans = Math.min(ans, next1 + next2);
            }
        }
        dp[L][R][P] = ans;
        return ans;
    }





}
