package UltimateAlgo2024.DynamicProgramming.TreeDP.Part1;

// https://leetcode.com/problems/largest-bst-subtree/

/**
 * 可能性
 * 1. 不包含head：head.left的最大BST大小，head.right的最大BST大小，求最大
 * 2. 包含head：head.left是否是BST，head.right是否是BST，left的max < head, right的min > head
 */
public class Code01_LargestBstSubtree {

    // 不要提交这个类
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static int largestBSTSubtree(TreeNode root) {
        return f(root).maxBsdSize;
    }

    public static class Info {
        public long max; // max val of this tree
        public long min;
        public boolean isBST;
        public int maxBsdSize;

        public Info(long _max, long _min, boolean bst, int _size) {
            max = _max;
            min = _min;
            isBST = bst;
            maxBsdSize = _size;
        }
    }

    public static Info f(TreeNode root) {
        if (root == null) {
            return new Info(Long.MIN_VALUE, Long.MAX_VALUE, true, 0);
        }
        Info infoL = f(root.left);
        Info infoR = f(root.right);
        long max = Math.max(root.val, Math.max(infoL.max, infoR.max));
        long min = Math.min(root.val, Math.min(infoL.min, infoR.min));
        boolean isBST = infoL.isBST && infoR.isBST && root.val > infoL.max && root.val < infoR.min;
        int maxBsdSize;
        if (isBST) {
            maxBsdSize = infoL.maxBsdSize + infoR.maxBsdSize + 1;
        } else {
            maxBsdSize = Math.max(infoL.maxBsdSize, infoR.maxBsdSize);
        }
        return new Info(max, min, isBST, maxBsdSize);
    }


}
