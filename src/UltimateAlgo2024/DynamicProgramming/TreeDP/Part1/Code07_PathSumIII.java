package UltimateAlgo2024.DynamicProgramming.TreeDP.Part1;

import java.util.Map;

// https://leetcode.com/problems/path-sum-iii/
// 分别以x点为结尾的情况下有几条路径满足题意
// 需要以每个节点开始的前缀和信息
// sum - tar 出现几次
public class Code07_PathSumIII {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    // key: preSum val: count
    public static Map<Integer, Integer> preSum;

    public static int ans = 0;

    public int pathSum(TreeNode root, int targetSum) {
        preSum.put(0, 1);
        return f(root, 0, targetSum);
    }

    // sum : 从头节点出发，来到x的时候，上方累加和是多少
    // 路径必须以x作为结尾，路径累加和是target的路径数量，累加到全局变量ans上
    public static int f(TreeNode x, int sum, int tar) {
        if (x == null) {
            return 0;
        }
        int newSum = sum + x.val;
        preSum.put(x.val, preSum.getOrDefault(x.val, 0) + 1);
        preSum.put(newSum, preSum.getOrDefault(newSum, 0) + 1);
        ans += preSum.get(tar - newSum) + f(x.left, newSum, tar) + f(x.right, newSum, tar);
        return ans;
    }
}
