package UltimateAlgo2024.Tree.Others;

// https://leetcode.com/problems/validate-binary-search-tree/
public class Code05_ValidateBinarySearchTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
    }

    public static boolean ans;

    public boolean isValidBST(TreeNode root) {
        ans = true;
        if (root != null) {
            process(root);
        }
        return ans;
    }

    // return [min, max]
    int[] process(TreeNode root) {
        int[] vals = {root.val, root.val};
        if (root.left != null) {
            int[] l = process(root.left);
            if (l[1] >= root.val) {
                ans = false;
            }
            vals[0] = l[0];
        }
        if (root.right != null) {
            int[] r = process(root.right);
            if (r[0] <= root.val) {
                ans = false;
            }
            vals[1] = r[1];
        }
        return vals;
    }




    public static long min, max;

    public static boolean isValidBST2(TreeNode head) {
        if (head == null) {
            min = Long.MAX_VALUE;
            max = Long.MIN_VALUE;
            return true;
        }
        boolean lok = isValidBST2(head.left);
        long lmin = min;
        long lmax = max;
        boolean rok = isValidBST2(head.right);
        long rmin = min;
        long rmax = max;
        min = Math.min(Math.min(lmin, rmin), head.val);
        max = Math.max(Math.max(lmax, rmax), head.val);
        return lok && rok && lmax < head.val && head.val < rmin;
    }



}
