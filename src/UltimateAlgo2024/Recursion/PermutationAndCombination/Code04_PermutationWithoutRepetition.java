package UltimateAlgo2024.Recursion.PermutationAndCombination;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// https://leetcode.com/problems/permutations-ii/
// 剪枝：换过来的数如果和他本身相等则不走该分支
public class Code04_PermutationWithoutRepetition {

    public static List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
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
            HashSet<Integer> set = new HashSet<>();
            // who's beginning
            for (int i = idx; i < arr.length; i++) {
                if (!set.contains(arr[i])) {
                    swap(arr, idx, i);
                    process(arr, idx + 1);
                    swap(arr, idx, i);
                    set.add(arr[i]);
                }
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
