package UltimateAlgo2024.Tree.BFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
public class Code02_ZigzagLevelOrderTraversal {

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

    public static int l, r, isOdd, size;

    public static TreeNode[] queue = new TreeNode[2001];

    public static Stack<TreeNode> stack = new Stack<>();

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> ans = new ArrayList<>();
        if (root != null) {
            l = r = 0;
            isOdd = 1;
            queue[r++] = root;
            while (l < r) {
                size = r - l;
                List<Integer> curLevel = new ArrayList<>();
                while (size-- != 0) {
                    TreeNode cur = queue[l++];
                    if (cur.left != null) {
                        queue[r++] = cur.left;
                    }
                    if (cur.right != null) {
                        queue[r++] = cur.right;
                    }
                    if (isOdd != 1) {
                        stack.push(cur);
                    } else {
                        curLevel.add(cur.val);
                    }
                }
                if (isOdd != 1) {
                    while (!stack.isEmpty()) {
                        curLevel.add(stack.pop().val);
                    }
                }
                ans.add(curLevel);
                isOdd ^= 1;
            }
        }
        return ans;
    }
}
