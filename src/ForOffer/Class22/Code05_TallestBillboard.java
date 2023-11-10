package ForOffer.Class22;

import java.util.HashMap;

// 本题测试链接 : https://leetcode.com/problems/tallest-billboard/
public class Code05_TallestBillboard {

    public int tallestBillboard(int[] rods) {
        if (rods == null || rods.length == 0) {
            return 0;
        }
        // dp<key:diff of 2 set, val:sum of the smaller set> (baseline)
        // key 集合对的某个差
        // value 满足差值为key的集合对中，最好的一对，较小集合的累加和
        // 较大 -> value + key
        HashMap<Integer, Integer> dp = new HashMap<>(), last;
        dp.put(0, 0); // empty sets
        for (int num : rods) {
            if (num != 0) {
                // 老表推新表
                last = new HashMap<>(dp);
                for (int d : last.keySet()) {
                    int diffMore = last.get(d); // sum最大的一对，较小集合的累加和
                    // x决定放入比较大的那个
                    dp.put(d + num, Math.max(diffMore, dp.getOrDefault(num + d, 0)));
                    // x决定放入比较小的那个
                    // 新的差值 Math.abs(x - d)
                    // 之前差值为Math.abs(x - d)，的那一对，就要和这一对，决策一下
                    // 之前那一对，较小集合的累加和diffXD
                    int diffXD = dp.getOrDefault(Math.abs(num - d), 0);
                    if (d >= num) { // d和num的关系，决定了哪边的集合是新的sum较小的集合
                        dp.put(d - num, Math.max(diffMore + num, diffXD)); // 还是原来小集合较小
                    } else {
                        dp.put(num - d, Math.max(diffMore + d, diffXD)); // 原来较大的集合现在较小
                    }
                }
            }
        }
        return dp.get(0);
    }


}
