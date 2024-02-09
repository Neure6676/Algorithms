package UltimateAlgo2024.SweepLine;

import java.util.Arrays;
import java.util.PriorityQueue;

// https://leetcode.com/problems/meeting-rooms-ii/
public class Code02_MeetingRoomsII {

    // 最多有多少线段重合
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int ans = 0;
        for (int i = 0; i < intervals.length; i++) {
            while (!heap.isEmpty() && heap.peek() <= intervals[i][0]) {
                heap.poll();
            }
            heap.add(intervals[i][1]);
            ans = Math.max(ans, heap.size());
        }
        return ans;
    }
}
