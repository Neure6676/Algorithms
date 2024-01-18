package UltimateAlgo2024.Tree.PathSum;

// https://leetcode.com/problems/path-sum/
public class Code01_PathSumI {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
    }

    public static boolean ans;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        ans = false;
        if (root != null) {
            process(root, targetSum);
        }
        return ans;
    }

    void process(TreeNode root, int newT) {
        if (root.left == null && root.right == null) {
            if (root.val == newT) ans = true;
        } else {
            // not leaf
            newT -= root.val;
            if (root.left != null) {
                process(root.left, newT);
            }
            if (root.right != null) {
                process(root.right, newT);
            }
        }
    }
}
