package UltimateAlgo2024.SlidingWindow;

// https://leetcode.com/problems/gas-station/
public class Code04_GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        for (int l = 0, r = 0, sum = 0, len = 0; l < n; l++) {
            while (sum >= 0) {
                if (len == n) {
                    return l;
                }
                r = (l + (len++)) % n;
                sum += gas[r] - cost[r];
            }
            len--;
            sum -= gas[l] - cost[l];
        }
        return -1;
    }
}
