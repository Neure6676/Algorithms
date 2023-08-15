package ForOffer.Class14;

/**
 * 给定一个棵完全二叉树，返回这棵树的节点个数，要求时间复杂度小于O(树的节点数)
 * 本题测试链接 : https://leetcode.com/problems/count-complete-tree-nodes/
 */
public class Code04_CompleteTreeNodeNumber {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    // 提交如下的方法
    public static int countNodes(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return bs(head, 1, mostLeftLevel(head, 1));
    }

    // 当前来到node节点，node节点在level层，总层数是h
    // 返回node为头的子树(必是完全二叉树)，有多少个节点
    public static int bs(TreeNode node, int level, int h) {
        if (level == h) {
            return 1;
        }
        if (mostLeftLevel(node.right    , level + 1) != h) {
            // 右子树是CBT
            return (1 << (h - level - 1))  + bs(node.left, level + 1, h);
        } else {
            return (1 << (h - level)) + bs(node.right, level + 1, h);
        }
    }


    // 如果node在第level层，
    // 求以node为头的子树，最大深度是多少
    // node为头的子树，一定是完全二叉树
    public static int mostLeftLevel(TreeNode node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }
}
