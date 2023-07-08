package SystematicClass.Class13;

public class Code03_IsBalanceBT {
    /**
     * verify if this tree is a balance binary tree
     *
     * 1. if left tree balance
     * 2. if right tree balance
     * 3. left_height - right_height < 2 ?
     */
    private static class Node {
        private int val;
        private Node left;
        private Node right;

        public Node(int _val) {
            val = _val;
        }
    }

    public static boolean isBalanced1(Node head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(Node head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }



    public static boolean isBalanced2(Node root) {
        if (root == null) {
            return true;
        }
        return process(root).isBalanced;
    }

    //信息体：1 是否平；2 高度
    private static class info{
        private boolean isBalanced;
        private int height;

        public info(boolean i, int h){
            isBalanced = i;
            height = h;
        }
    }

    public static info process(Node x) {
        if (x == null) {
            return new info(true, 0);
        }
        info leftInfo = process(x.left);
        info rightInfo = process(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalance = true;
        if (!leftInfo.isBalanced || !rightInfo.isBalanced || Math.abs(leftInfo.height - rightInfo.height) > 1) {
            isBalance = false;
        }
        return new info(isBalance, height);
    }





    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(head) != isBalanced2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
