package UltimateAlgo2024.Tree.Completeness;


import UltimateAlgo2024.Tree.Serialization.Code03_PreorderInorderBuildBinaryTree;

// 验证完全二叉树
// https://leetcode.com/problems/check-completeness-of-a-binary-tree/
public class Code01_CompletenessOfBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
    }

    /**
     * BFS
     * 每到一个点判断
     * 1. 有右无左 false
     * 2. 一旦孩子不全，接下来的节点必须都是叶子节点
     */
    public static int MAXN = 101;

    public static TreeNode[] queue = new TreeNode[MAXN];

    public static int l, r;

    public static boolean isCompleteTree(TreeNode h) {
        if (h == null) {
            return true;
        }
        l = r = 0;
        queue[r++] = h;
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        while (l < r) {
            h = queue[l++];
            if ((h.left == null && h.right != null) || (leaf && (h.left != null || h.right != null))) {
                return false;
            }
            if (h.left != null) {
                queue[r++] = h.left;
            }
            if (h.right != null) {
                queue[r++] = h.right;
            }
            if (h.left == null || h.right == null) {
                leaf = true;
            }
        }
        return true;
    }

}
