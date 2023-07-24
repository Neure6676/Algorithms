package ForOffer.Class5;

import java.util.Stack;

/**
 * 已知一棵搜索二叉树上没有重复值的节点，现在有一个数组arr，是这棵搜索二叉树先序遍历的结果，请根据arr生成整棵树并返回头节点
 * 本题测试链接 : https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 */
public class Code01_ConstructBinarySearchTreeFromPreorderTraversal {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode bstFromPreorder1(int[] pre) {
        if (pre == null || pre.length == 0) {
            return null;
        }
        return process(pre, 0, pre.length - 1);
    }

    private TreeNode process(int[] pre, int L, int R) {
        if (L > R) {
            return null;
        }
        // 找第一个大于L的 L是头
        int firstBig = L + 1;
        for (; firstBig <= R; firstBig++) {
            if (pre[firstBig] > pre[L]) {
                break;
            }
        }
        TreeNode head = new TreeNode(pre[L]);
        head.left = process(pre, L + 1, firstBig - 1);
        head.right = process(pre, firstBig, R);
        return head;
    }


    // 继续优化 去掉遍历过程 用单调栈
    public TreeNode bstFromPreorder(int[] pre) {
        if (pre == null || pre.length == 0) {
            return null;
        }
        int N = pre.length;
        // 单调栈 生成每个位置右边最近且比它大的位置
        int[] nearBig = new int[N];
        for (int i = 0; i < N; i++) {
            nearBig[i] = -1;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && pre[stack.peek()] < pre[i]) {
                nearBig[stack.pop()] = i;
            }
            stack.push(i);
        }
        return process2(pre, 0, N - 1, nearBig);
    }

    private TreeNode process2(int[] pre, int L, int R, int[] nearBig) {
        if (L > R) {
            return null;
        }
        int firstBig = (nearBig[L] == -1 || nearBig[L] > R) ? R + 1 : nearBig[L];
        TreeNode head = new TreeNode(pre[L]);
        head.left = process2(pre, L + 1, firstBig - 1, nearBig);
        head.right = process2(pre, firstBig, R, nearBig);
        return head;
    }
}
