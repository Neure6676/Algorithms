package UltimateAlgo2024.Tree.Serialization;

import java.util.HashMap;

// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
public class Code03_PreorderInorderBuildBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) {
            return null;
        }
        TreeNode ans = process(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
        return ans;
    }

    TreeNode process(int[] preorder, int[] inorder, int ps, int pe, int is, int ie) {
        TreeNode ans = new TreeNode(preorder[ps]);
        if (ps == pe) {
            return ans;
        }
        if (inorder[is] == preorder[ps]) {
            // no left child
            ans.left = null;
            ans.right = process(preorder, inorder, ps + 1, pe, is + 1, ie);
        } else {
            int idxLeft = is;
            while (inorder[idxLeft] != preorder[ps]){
                idxLeft++;
            }
            ans.left = process(preorder, inorder, ps + 1, ps + idxLeft - is, is, idxLeft - 1);
            int idxRight = idxLeft + 1;
            if (idxRight > ie) {
                ans.right = null;
            } else {
                ans.right = process(preorder, inorder, ps + idxLeft - is + 1, pe, idxRight, ie);
            }
        }
        return ans;
    }


    public static TreeNode buildTree1(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        return f(pre, 0, pre.length - 1, in, 0, in.length - 1, map);
    }

    public static TreeNode f(int[] pre, int l1, int r1, int[] in, int l2, int r2, HashMap<Integer, Integer> map) {
        if (l1 > r1) {
            return null;
        }
        TreeNode head = new TreeNode(pre[l1]);
        if (l1 == r1) {
            return head;
        }
        int k = map.get(pre[l1]);
        // pre : l1(........)[.......r1]
        // in  : (l2......)k[........r2]
        // (...)是左树对应，[...]是右树的对应
        head.left = f(pre, l1 + 1, l1 + k - l2, in, l2, k - 1, map);
        head.right = f(pre, l1 + k - l2 + 1, r1, in, k + 1, r2, map);
        return head;
    }
}
