package UltimateAlgo2024.BinarySearch;

// https://leetcode.com/problems/find-peak-element/
public class Code04_FindPeakElement {

    // Input: nums = [1,2,4,3,1]
    // Output: 2
    // nums[-1] = nums[n] = -∞
    public int findPeakElement(int[] arr) {
        if (arr == null) {
            return -1;
        }

        int ans = -1;
        int N = arr.length;

        if (N == 1) {
            return 0;
        }

        // check 0 and n-1
        if (arr[0] > arr[1]) {
            return 0;
        }
        if (arr[N - 1] > arr[N - 2]) {
            return N - 1;
        }

        //there must be a peak element [1, n-2]
        int l = 1, r = N -2, m = 0;
        while (r >= l) {
            m = l + (r - l) / 2;
            if (arr[m] > arr[m - 1] && arr[m] > arr[m + 1]) {
                return m;
            } else if (arr[m] < arr[m - 1]){
                // there must be a peak element [l, m - 1]
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }
}
