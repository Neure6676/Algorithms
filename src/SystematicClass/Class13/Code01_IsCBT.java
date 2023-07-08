package SystematicClass.Class13;

import java.util.LinkedList;
import java.util.Queue;

/**
 * To verify if this tree is a Completed Binary Tree
 *
 * 1. if a Node has right child but no left child -> false
 * 2. all the rest nodes after first not full node must be leaf node.
 */
public class Code01_IsCBT {
    private static class Node {
        private int val;
        private Node left;
        private Node right;

        public Node(int _val) {
            val = _val;
        }
    }

    public static Boolean isCBT1(Node root) {
        if (root == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        // have you ever meet a not full node?
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            l = cur.left;
            r = cur.right;
            if (
                    (l == null && r != null) || (leaf && (l != null || r != null))
            ) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

    public static boolean isCBT2(Node root) {
        if (root == null) {
            return true;
        }

        return true;
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
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
