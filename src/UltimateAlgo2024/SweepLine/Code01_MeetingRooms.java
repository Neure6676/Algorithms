package UltimateAlgo2024.SweepLine;

import java.util.Arrays;

// https://leetcode.com/problems/meeting-rooms/description/
public class Code01_MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i - 1][1] > intervals[i][0]) {
                return false;
            }
        }
        return true;
    }
}
