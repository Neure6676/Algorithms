package UltimateAlgo2024.Tree.Others;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/path-sum-ii/
// 恢复现场 -> 全局变量
public class Code03_PathSumII {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
    }

    public static List<List<Integer>> ans = new ArrayList<>();

    public static List<Integer> cur = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        ans.clear();
        if (root != null) {
            process(root, targetSum);
        }
        return ans;
    }

    void process(TreeNode root, int newT) {
        if (root.left == null && root.right == null) {
            if (newT == root.val) {
                cur.add(root.val);
                copy(ans, cur);
                // 恢复现场
                cur.remove(cur.size() - 1);
            }
        } else {
            cur.add(root.val);
            newT = newT - root.val;
            if (root.left != null) {
                process(root.left, newT);
            }
            if (root.right != null) {
                process(root.right, newT);
            }
            // 恢复现场
            cur.remove(cur.size() - 1);
        }
    }

    // 为配合全局变量
    void copy(List<List<Integer>> ans, List<Integer> cur) {
        List<Integer> temp = new ArrayList<>();
        for (Integer num : cur) {
            temp.add(num);
        }
        ans.add(temp);
    }

    public static void main(String[] args) {
        String str = "1->2";
        StringBuilder sb = new StringBuilder(str);
        System.out.println(sb.delete(sb.length() - 1, sb.length()));
    }
}
