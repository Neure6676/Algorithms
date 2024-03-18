package UltimateAlgo2024.DynamicProgramming.TreeDP.Part1;

// https://leetcode.com/problems/house-robber-iii/
public class Code05_HouseRobberiii {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static class Info {
        public int hasHead;
        public int noHead;

        public Info(int _h, int _n) {
            hasHead = _h;
            noHead = _n;
        }
    }

    public int rob(TreeNode root) {
        Info info = f(root);
        return Math.max(info.hasHead, info.noHead);
    }

    public static Info f(TreeNode x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info infoL = f(x.left);
        Info infoR = f(x.right);
        int hasHead = x.val + infoL.noHead + infoR.noHead;
        int noHead = Math.max(infoL.noHead, infoL.hasHead) + Math.max(infoR.hasHead, infoR.noHead);
        return new Info(hasHead, noHead);
    }
}
