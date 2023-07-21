package SystematicClass.Class40;

import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 牛牛家里一共有n袋零食, 第i袋零食体积为v[i]，背包容量为w，牛牛想知道在总体积不超过背包容量的情况下,
 * 一共有多少种零食放法，体积为0也算一种放法
 * 1 <= n <= 30, 1 <= w <= 2 * 10^9，v[I] (0 <= v[i] <= 10^9）
 *
 * https://www.nowcoder.com/questionTerminal/d94bb2fa461d42bcb4c0f2b94f5d4281
 */
public class Code02_SnacksWays {

    public static int ways1(int[] arr, int w) {
        // arr[0...]
        return process(arr, 0, w);
    }

    // 从左往右的经典模型
    // 还剩的容量是rest，arr[index...]自由选择，
    // 返回选择方案
    // index ： 0～N
    // rest : 0~w
    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        // rest>=0,
        if (index == arr.length) { // 无零食可选
            return 1;
        }
        // rest >=0
        // 有零食index
        // index号零食，要 or 不要
        // index, rest
        // (index+1, rest)
        // (index+1, rest-arr[i])
        int next1 = process(arr, index + 1, rest); // 不要
        int next2 = process(arr, index + 1, rest - arr[index]); // 要
        return next1 + (next2 == -1 ? 0 : next2);
    }


    // 分治法
    public static long ways(int[] arr, int bag) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] <= bag ? 2 : 1;
        }
        int mid = (arr.length) / 2;
        // 填左右map
        TreeMap<Long, Long> lMap = new TreeMap<>();
        long ways = process(arr, 0, 0, mid, bag, lMap);
        TreeMap<Long, Long> rMap = new TreeMap<>();
        ways += process(arr, mid + 1, 0, arr.length - 1, bag, rMap);
        // 填右前缀map
        TreeMap<Long, Long> rPre = new TreeMap<>();
        long pre = 0;
        for (Entry<Long, Long> entry : rMap.entrySet()) {
            pre += entry.getValue();
            rPre.put(entry.getKey(), pre);
        }
        for (Entry<Long, Long> entry : lMap.entrySet()) {
            long lWeight = entry.getKey();
            long lWays = entry.getValue();
            Long floor = rPre.floorKey(bag - lWays);
            if (floor != null) {
                long rWays = rPre.get(floor);
                ways += lWays * rWays;
            }
        }
        return ways + 1;  //1是左右都没拿
    }




    public static long process(int[] arr, int index, long sum, int end, int bag, TreeMap<Long, Long> map) {
        if (sum > bag) {
            return 0;
        }
        if (index > end) {
            if (sum != 0) {
                if (!map.containsKey(sum)) {
                    map.put(sum, 1L);
                } else {
                    map.put(sum, map.get(sum) + 1);
                }
                return 1;
            } else {
                return 0;
            }
        } else {
            // 1.没要当前位置
            long ways = process(arr, index + 1, sum, end, bag, map);
            // 2.要当前位置
            ways += process(arr, index + 1, sum + arr[index], end, bag, map);
            return ways;
        }
    }
}
