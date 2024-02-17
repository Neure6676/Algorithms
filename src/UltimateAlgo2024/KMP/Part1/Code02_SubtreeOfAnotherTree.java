package UltimateAlgo2024.KMP.Part1;

// https://leetcode.com/problems/subtree-of-another-tree/
public class Code02_SubtreeOfAnotherTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
        }
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root != null && subRoot != null) {
            return same(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }
        return subRoot == null;
    }

    public static boolean same(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 != null && t2 != null) {
            return t1.val == t2.val && same(t1.left, t2.left) && same(t1.right, t2.right);
        }
        return false;
    }
}
