package UltimateAlgo2024.Recursion.PermutationAndCombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/subsets-ii/
// 减枝：提前舍弃掉会导致重复或不符合要求的情况
// 过滤：所有答案都出来的情况下去掉某些
public class Code02_Combinations {

    public static List<Integer> list = new ArrayList<>();

    public static List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        ans.clear();  // clear
        if (nums.length > 0) {
            list.clear();  // clear
            Arrays.sort(nums);
            process(nums, 0, list);
        }
        return ans;
    }

    void process(int[] arr, int i, List<Integer> list) {
        if (i == arr.length) {
            // 不能ans.add(list);
            List<Integer> cur = new ArrayList<>(list);
            ans.add(cur);
        } else {
            int cur = arr[i];
            int idx = i;
            while (idx < arr.length && arr[idx] == cur) { // 防止越界
                idx++;
            }
            int size = idx - i;
            process(arr, idx, list);
            for (int k = 0; k < size; k++) {
                list.add(cur);
                process(arr, idx, list);
            }
            // 恢复现场
            for (int k = 0; k < size; k++) {
                list.remove(list.size() - 1);
            }
        }
    }
}
