package ForOffer.Class29;

import java.util.ArrayList;
import java.util.Arrays;

// https://leetcode.com/problems/merge-intervals/description/
public class Code03_MergeIntervals {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        ArrayList<int[]> list = new ArrayList<>();
        int[] last = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (cur[0] > last[1]) {
                list.add(last);
                last = cur;
            } else {
                last[1] = Math.max(last[1], cur[1]);
            }
        }
        list.add(last);
        int[][] ans = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

}
