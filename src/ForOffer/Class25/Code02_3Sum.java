package ForOffer.Class25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

// https://leetcode.com/problems/3sum/
public class Code02_3Sum {

    //Input: nums = [-1,0,1,2,-1,-4]
    //Output: [[-1,-1,2],[-1,0,1]]
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int N = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = N - 1; i > 1; i--) {
            if (i == N - 1 || nums[i] != nums[i + 1]) {
                List<List<Integer>> otherTwo = twoSum(nums, i - 1, -nums[i]);
                for (List<Integer> cur : otherTwo) {
                    cur.add(nums[i]);
                    ans.add(cur);
                }
            }
        }
        return ans;
    }

    public static List<List<Integer>> twoSum(int[] arr, int end, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        int L = 0;
        int R = end;
        while (L < R) {
            if (arr[L] + arr[R] > n) {
                R--;
            } else if (arr[L] + arr[R] < n) {
                L++;
            } else {
                if (L == 0 || arr[L - 1] != arr[L]) {
                    List<Integer> cur = new ArrayList<>();
                    cur.add(arr[L]);
                    cur.add(arr[R]);
                    ans.add(cur);
                }
                L++;
            }
        }
        return ans;
    }


}
