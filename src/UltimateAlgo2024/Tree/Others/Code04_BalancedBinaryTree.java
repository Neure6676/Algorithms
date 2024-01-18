package UltimateAlgo2024.Tree.Others;

// https://leetcode.com/problems/balanced-binary-tree/
public class Code04_BalancedBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
    }

    public static boolean ans;

    public boolean isBalanced(TreeNode root) {
        ans = true;
        if (root != null) {
            int h = process(root);
        }
        return ans;
    }

    int process(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lh = process(root.left);
        int rh = process(root.right);
        if (Math.abs(lh - rh) > 1) {
            ans = false;
        }
        return Math.max(lh, rh) + 1;
    }

}
