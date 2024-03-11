package UltimateAlgo2024.KMP.Part1;

import java.util.ArrayList;

// https://leetcode.com/problems/subtree-of-another-tree/
public class Code02_SubtreeOfAnotherTree {

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

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root != null && subRoot != null) {
            return same(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }
        return subRoot == null;
    }

    public static boolean same(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 != null && t2 != null) {
            return t1.val == t2.val && same(t1.left, t2.left) && same(t1.right, t2.right);
        }
        return false;
    }


    // 方法2
    // 二叉树先序序列化 + KMP算法匹配
    // 时间复杂度O(n + m)
    public boolean isSubtree2(TreeNode t1, TreeNode t2) {
        if (t1 != null && t2 != null) {
            ArrayList<String> l1 = new ArrayList<>();
            ArrayList<String> l2 = new ArrayList<>();
            serial(t1, l1);
            serial(t2, l2);
            return KMP(l1, l2);
        }
        return t2 == null;
    }

    public static void serial(TreeNode t, ArrayList<String> l) {
        if (t == null) {
            l.add(null);
        } else {
            l.add(String.valueOf(t.val));
            serial(t.left, l);
            serial(t.right, l);
        }
    }

    public static boolean KMP(ArrayList<String> l1, ArrayList<String> l2) {
        int n = l1.size(), m = l2.size(), i = 0, j = 0;
        int[] next = nextArray(l2, m);
        while (i < n && j < m) {
            if (isEqual(l1.get(i), l2.get(j))) {
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = next[j];
            }
        }
        return j == m;
    }

    public static int[] nextArray(ArrayList<String> l, int m) {
        if (m == 1) {
            return new int[] { -1 };
        }
        int[] next = new int[m];
        next[0] = -1;
        next[1] = 0;
        int i = 2, cn = 0;
        while (i < m) {
            if (isEqual(l.get(i - 1), l.get(cn))) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static boolean isEqual(String a, String b) {
        if (a == null && b == null) {
            return true;
        }
        if (a != null && b != null) {
            return a.equals(b);
        }
        return false;
    }
}
