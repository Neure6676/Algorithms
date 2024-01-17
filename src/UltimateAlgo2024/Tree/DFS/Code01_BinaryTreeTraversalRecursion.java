package UltimateAlgo2024.Tree.DFS;

public class Code01_BinaryTreeTraversalRecursion {

    public static class Node {
        private int val;
        private Node left;
        private Node right;

        public Node(int _val) {
            val = _val;
        }
    }

    public static void preOrder(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.val);
        preOrder(head.left);
        preOrder(head.right);
    }

    public static void inOrder(Node head) {
        if (head == null) {
            return;
        }
        preOrder(head.left);
        System.out.println(head.val);
        preOrder(head.right);
    }

    public static void postOrder(Node head) {
        if (head == null) {
            return;
        }
        preOrder(head.left);
        preOrder(head.right);
        System.out.println(head.val);
    }
}
