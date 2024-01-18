package UltimateAlgo2024.Tree.Others;

import UltimateAlgo2024.Tree.Completeness.Code02_CountCompleteTreeNodes;

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
public class Code01_LowestCommonAncestor {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode l = lowestCommonAncestor(root.left, p, q);
        TreeNode r = lowestCommonAncestor(root.right, p, q);
        if (l != null && r != null) { // root is the common ancestor
            return root;
        }
        if (l == null && r == null) {
            return null;
        }
        return l != null ? l : r;
    }


}
