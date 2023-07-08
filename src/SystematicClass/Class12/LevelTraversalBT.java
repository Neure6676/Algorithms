package SystematicClass.Class12;

import java.util.LinkedList;
import java.util.Queue;

public class LevelTraversalBT {
    private static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

//    public static void BFS(Node root) {
//        //先考虑特殊的：
//        if (root == null) {
//            return;
//        }
//        Queue<Node> queue = new LinkedList<>();  //Queue用LinkedList实现
//        queue.add(root);
//        while (!queue.isEmpty()) {
//            Node cur = queue.poll();
//            System.out.println(cur.value);
//            if (cur.left != null) {
//                queue.add(cur.left);
//            }
//            if (cur.right != null){
//                queue.add(cur.right);
//            }
//        }
//    }

    public static void BFS(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        BFS(head);
        System.out.println("========");
    }



}
