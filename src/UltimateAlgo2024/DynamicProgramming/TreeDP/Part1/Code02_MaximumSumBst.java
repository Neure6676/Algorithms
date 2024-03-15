package UltimateAlgo2024.DynamicProgramming.TreeDP.Part1;

// https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
public class Code02_MaximumSumBst {

    // 不要提交这个类
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static int maxSumBST(TreeNode root) {
        return f(root).maxBsdSum;
    }

    public static class Info {
        public int max; // max val of this tree
        public int min;
        public boolean isBST;
        public int maxBsdSum;
        public int sum;

        public Info(int _max, int _min, boolean bst, int _maxBsdSum, int _sum) {
            max = _max;
            min = _min;
            isBST = bst;
            maxBsdSum = _maxBsdSum;
            sum = _sum;
        }
    }

    public static Info f(TreeNode root) {
        if (root == null) {
            return new Info(Integer.MIN_VALUE, Integer.MAX_VALUE, true, 0, 0);
        }
        Info infoL = f(root.left);
        Info infoR = f(root.right);
        int max = Math.max(root.val, Math.max(infoL.max, infoR.max));
        int min = Math.min(root.val, Math.min(infoL.min, infoR.min));
        boolean isBST = infoL.isBST && infoR.isBST && root.val > infoL.max && root.val < infoR.min;
        int sum = infoL.sum + infoR.sum + root.val;
        int maxBsdSum = Math.max(infoL.maxBsdSum, infoR.maxBsdSum);
        if (isBST) {
            maxBsdSum = Math.max(sum, maxBsdSum);
        }
        return new Info(max, min, isBST, maxBsdSum, sum);
    }
}
