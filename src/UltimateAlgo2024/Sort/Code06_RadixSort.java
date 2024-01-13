package UltimateAlgo2024.Sort;

import java.util.Arrays;

// https://leetcode.com/problems/sort-an-array/
public class Code06_RadixSort {

    public static int BASE = 1000;

    public static int MAXN = 50001;

    public static int[] help = new int[MAXN];

    public static int[] counts = new int[BASE]; // 10个桶

    public static int[] sortArray(int[] arr) {
        if (arr.length > 1) {
            // 如果会溢出，那么要改用long类型数组来排序
            int n = arr.length;
            // 找到数组中的最小值
            int min = arr[0];
            for (int i = 1; i < n; i++) {
                min = Math.min(min, arr[i]);
            }
            int max = 0;
            for (int i = 0; i < n; i++) {
                // 数组中的每个数字，减去数组中的最小值，就把arr转成了非负数组
                arr[i] -= min;
                // 记录数组中的最大值
                max = Math.max(max, arr[i]);
            }
            // 根据最大值在BASE进制下的位数，决定基数排序做多少轮
            radixSort(arr, n, bits(max));
            for (int i = 0; i < n; i++) {
                arr[i] += min;
            }
        }
        return arr;
    }


    // 返回number在BASE进制下有几位
    public static int bits(int num) {
        int ans = 0;
        while (num > 0) {
            num /= BASE;
            ans++;
        }
        return ans;
    }

    // arr内要保证没有负数
    // n是arr的长度
    // bits是arr中最大值在BASE进制下有几位
    public static void radixSort(int[] arr, int n, int bits) {
        // 理解的时候可以假设BASE = 10
        for (int offset = 1; bits > 0; offset *= BASE, bits--) {
            Arrays.fill(counts, 0);
            for (int i = 0; i < n; i++) {
                // 数字提取某一位的技巧
                counts[(arr[i] / offset) % BASE]++;
            }
            // 处理成前缀次数累加的形式
            for (int i = 1; i < BASE; i++) {
                counts[i] = counts[i] + counts[i - 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                // 前缀数量分区的技巧
                // 数字提取某一位的技巧
                help[--counts[(arr[i] / offset) % BASE]] = arr[i];
            }
            for (int i = 0; i < n; i++) {
                arr[i] = help[i];
            }
        }
    }
}
