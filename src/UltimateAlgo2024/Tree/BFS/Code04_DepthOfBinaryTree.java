package UltimateAlgo2024.Tree.BFS;

// https://leetcode.com/problems/maximum-depth-of-binary-tree/
// https://leetcode.com/problems/minimum-depth-of-binary-tree/
// 二叉树递归套路：左边的信息 右边的信息 整合
public class Code04_DepthOfBinaryTree {

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

    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    // 最小深度一定要到叶子节点
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // root 是叶节点
        if (root.left == null && root.right == null) {
            return 1;
        }
        int leftDepth = Integer.MAX_VALUE;
        int rightDepth = Integer.MAX_VALUE;
        // 如果为空 不能调递归
        // 因为必须将叶子节点走到头
        if (root.left != null) {
            leftDepth = minDepth(root.left);
        }
        if (root.right != null) {
            rightDepth = minDepth(root.right);
        }
        return Math.min(leftDepth, rightDepth) + 1;
    }


}
