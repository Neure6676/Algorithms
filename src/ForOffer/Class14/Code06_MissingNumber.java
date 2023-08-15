package ForOffer.Class14;

// 测试链接：https://leetcode.com/problems/first-missing-positive/
public class Code06_MissingNumber {

    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int N = nums.length;
        int L = 0; // 若L=5，则有效区是0，1，2，3，4
        int R = N;
        while (R != L) {
            if (nums[L] == L + 1) {
                L++; // 有效区右扩
            } else if (nums[L] <= L || nums[L] > R || nums[nums[L] - 1] == nums[L]){
                swap(nums, L, --R);
            } else {
                swap(nums, L, nums[L] - 1);  // 它该在的地方
            }
        }
        return R + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
