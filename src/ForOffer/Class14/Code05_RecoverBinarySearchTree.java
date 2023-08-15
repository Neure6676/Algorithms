package ForOffer.Class14;

// 本题测试链接 : https://leetcode.com/problems/recover-binary-search-tree/
public class Code05_RecoverBinarySearchTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int v) {
            val = v;
        }
    }

    // BST的中序遍历是递增的 出现降序就有问题
    // 可能出现两组或一组降序：14325 42315
    // 统一：第一个错点：第一次降序的第一个
    //      第二个错点：最后一次降序的第二个
    public void recoverTree(TreeNode root) {
        TreeNode[] errors = twoErrors(root);
        if (errors[0] != null && errors[1] != null) {
            int tmp = errors[0].val;
            errors[0].val = errors[1].val;
            errors[1].val = tmp;
        }
    }

    //      3 cur
    //   1m    4
    //       2
    public static TreeNode[] twoErrors(TreeNode head) {
        TreeNode[] ans = new TreeNode[2];
        if (head == null) {
            return ans;
        }
        TreeNode cur = head;
        TreeNode mostRight = null;
        TreeNode pre = null;
        TreeNode e1 = null;
        TreeNode e2 = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            if (pre != null && pre.val >= cur.val) {
                e1 = e1 == null ? pre : e1;
                e2 = cur;
            }
            pre = cur;
            cur = cur.right;
        }
        ans[0] = e1;
        ans[1] = e2;
        return ans;
    }



}
