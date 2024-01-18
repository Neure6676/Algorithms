package UltimateAlgo2024.Tree.Others;

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
public class Code02_LowestCommonAncestorBinarySearch {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // root从上到下
        // 如果先遇到了p，说明p是答案
        // 如果先遇到了q，说明q是答案
        // 如果root在p~q的值之间，不用管p和q谁大谁小，只要root在中间，那么此时的root就是答案
        // 如果root在p~q的值的左侧，那么root往右移动
        // 如果root在p~q的值的右侧，那么root往左移动
        while (root.val != p.val && root.val != q.val) {
            if (root.val > Math.min(p.val, q.val) && root.val < Math.max(p.val, q.val)) {
                break;
            }
            root = root.val < Math.min(p.val, q.val) ? root.right : root.left;
        }
        return root;
    }
}
