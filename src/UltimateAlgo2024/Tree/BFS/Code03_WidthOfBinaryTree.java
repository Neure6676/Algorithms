package UltimateAlgo2024.Tree.BFS;

// https://leetcode.com/problems/maximum-width-of-binary-tree/
// 给每个节点编号，想象在数组中，空节点所在的位置空着
// BFS的同时把维护一个“编号”队列

// 注意TreeNode cur = queue[l++]; int curIdx = idxQueue[l++];
// 第一次l不能++ ！！！
public class Code03_WidthOfBinaryTree {

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

    public static int l, r,  size;
    public static int[] idxQueue = new int[3001];
    public static TreeNode[] queue = new TreeNode[3001];

    public int widthOfBinaryTree(TreeNode root) {
        int ans = 0;
        if (root != null) {
            l = r = 0;
            queue[r] = root;
            idxQueue[r++] = 1;
            while (l < r) {
                size = r - l;
                ans = Math.max(ans, idxQueue[r - 1] - idxQueue[l] + 1);
                while (size-- != 0) {
                    TreeNode cur = queue[l];
                    int curIdx = idxQueue[l++];
                    if (cur.left != null) {
                        queue[r] = cur.left;
                        idxQueue[r++] = 2 * curIdx;
                    }
                    if (cur.right != null) {
                        queue[r] = cur.right;
                        idxQueue[r++] = 2 * curIdx + 1;
                    }
                }
            }
        }
        return ans;
    }


}
