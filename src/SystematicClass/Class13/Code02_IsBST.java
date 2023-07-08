package SystematicClass.Class13;

public class Code02_IsBST {
    /**
     * 1. left tree is binary search tree
     * 2. right tree is bst
     * 3. left_max < root < right_min
     *
     * info:
     * 1. left_tree BST?
     * 2. right_tree BST?
     * 3. left_max
     * 4. right_min
     */
    private static class Node {
        private int val;
        private Node left;
        private Node right;

        public Node(int _val) {
            val = _val;
        }
    }

    public static boolean isBST(Node root) {
        if (root == null) {
            return true;
        }
        return process(root).isBST;
    }

    private static class info {
        private boolean isBST;
        private int max;
        private int min;

        public info(boolean i, int max, int min) {
            isBST = i;
            this.max = max;
            this.min = min;
        }
    }

    public static info process(Node x) {
        if (x == null) {
            return null;  // 不知道怎么设就返回空
        }
        info leftinfo = process(x.left);
        info rightinfo = process(x.right);

        boolean isBST = true;
        if (leftinfo != null && !leftinfo.isBST) {
            isBST = false;
        }
        if (rightinfo != null && !rightinfo.isBST) {
            isBST = false;
        }
        if (leftinfo != null && leftinfo.max >= x.val) {
            isBST = false;
        }
        if (rightinfo != null && rightinfo.min <= x.val) {
            isBST = false;
        }
        int max = x.val;
        if (x.left != null) {
            max = Math.max(max, leftinfo.max);
        }
        if (x.right != null) {
            max = Math.max(max, rightinfo.max);
        }

        int min = x.val;
        if (x.left != null) {
            min = Math.min(min, leftinfo.min);
        }
        if (x.right != null) {
            min = Math.min(min, rightinfo.min);
        }


        return new info(isBST, max, min);
    }
}
