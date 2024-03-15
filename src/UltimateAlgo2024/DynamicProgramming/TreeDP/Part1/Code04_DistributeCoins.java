package UltimateAlgo2024.DynamicProgramming.TreeDP.Part1;

// https://leetcode.com/problems/distribute-coins-in-binary-tree/
public class Code04_DistributeCoins {

    // 不要提交这个类
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static class Info {
        public int move;
        public int nodeSum;
        public int valSum;

        public Info(int _m, int _n, int _v) {
            move = _m;
            nodeSum = _n;
            valSum = _v;
        }
    }

    public int distributeCoins(TreeNode root) {
        return f(root).move;
    }

    public static Info f(TreeNode x) {
        if (x == null) {
            return new Info(0, 0, 0);
        }
        Info infoL = f(x.left);
        Info infoR = f(x.right);
        int nodeSum = infoL.nodeSum + infoR.nodeSum + 1;
        int valSum = infoL.valSum + infoR.valSum + x.val;
        int move = infoR.move + infoL.move + Math.abs(infoL.nodeSum - infoL.valSum) + Math.abs(infoR.nodeSum - infoR.valSum);
        return new Info(move, nodeSum, valSum);
    }
}
