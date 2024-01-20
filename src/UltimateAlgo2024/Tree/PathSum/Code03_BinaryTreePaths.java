package UltimateAlgo2024.Tree.PathSum;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/binary-tree-paths/
public class Code03_BinaryTreePaths {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
    }

    public static List<String> ans = new ArrayList<>();

    public static StringBuilder sb = new StringBuilder();

    public List<String> binaryTreePaths(TreeNode root) {
        ans.clear();
        if (root != null) {
            process(root);
        }
        return ans;
    }

    void process(TreeNode root) {
        if (root.left == null && root.right == null) {
            sb.append(root.val);
            int len = Integer.toString(root.val).length();
            copy(ans, sb);
            sb.delete(sb.length() - len, sb.length());
        } else {
            sb.append(root.val + "->");
            if (root.left != null) {
                process(root.left);
            }
            if (root.right != null) {
                process(root.right);
            }
            int len = Integer.toString(root.val).length() + 2;
            sb.delete(sb.length() - len, sb.length());
        }
    }

    void copy(List<String> ans, StringBuilder sb) {
        StringBuilder sb1 = new StringBuilder(sb);
        ans.add(sb1.toString());
    }
}
