package UltimateAlgo2024.Recursion.PermutationAndCombination;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/permutations/
public class Code03_Permutations {

    public static List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        ans.clear();  //1
        if (nums.length > 0) {
            process(nums, 0);
        }
        return ans;
    }

    public void process(int[] arr, int idx) {
        if (idx == arr.length) {
            List<Integer> list = new ArrayList<>();
            for (int i : arr) {
                list.add(i);
            }
            ans.add(list);
        } else {
            // who's beginning
            for (int i = idx; i < arr.length; i++) {
                swap(arr, idx, i);
                process(arr, idx + 1);
                // 清理现场：保持原始的状态走后面的分支
                swap(arr, idx, i);
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
