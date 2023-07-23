package ForOffer.Class4;

import java.util.Arrays;

/**
 * 老师想给孩子们分发糖果，有N个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。
 * 那么这样下来，返回老师至少需要准备多少颗糖果
 * 进阶：在原来要求的基础上，增加一个要求，相邻的孩子间如果分数一样，分的糖果数必须一样，返回至少需要准备多少颗糖果
 * 测试链接 : https://leetcode.com/problems/candy/
 */
public class Code05_CandyProblem {

    public static int candy(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] left = new int[N];
        for (int i = 1; i < N; i++) {
            if (arr[i] > arr[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        int[] right = new int[N];
        for (int i = N - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans += Math.max(right[i], left[i]);
        }
        return ans + N;
    }



}
