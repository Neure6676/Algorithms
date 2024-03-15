package UltimateAlgo2024.DynamicProgramming.TreeDP.Part1;

// https://leetcode.com/problems/diameter-of-binary-tree/
public class Code03_DiameterOfBinaryTree {

    // 不要提交这个类
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static class Info {
        int diameter;
        int height;

        public Info(int _d, int _h) {
            diameter = _d;
            height = _h;
        }
    }

    public int diameterOfBinaryTree(TreeNode root) {
        return f(root).diameter;
    }

    public static Info f(TreeNode x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info infoL = f(x.left);
        Info infoR = f(x.right);
        int height = Math.max(infoL.height, infoR.height) + 1;
        int diameter = Math.max(infoL.height + infoR.height, Math.max(infoL.diameter, infoR.diameter));
        return new Info(diameter, height);
    }
}
