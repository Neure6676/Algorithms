package UltimateAlgo2024.Tree;

import java.util.Stack;

public class Code2_BinaryTreeTraversalIteration {

    public static class Node {
        private int val;
        private Node left;
        private Node right;

        public Node(int _val) {
            val = _val;
        }
    }

    // https://leetcode.com/problems/binary-tree-preorder-traversal/
    public static void preOrder(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.print(cur.val + " ");
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    // https://leetcode.com/problems/binary-tree-inorder-traversal/
    // 1.子树左边界全部进栈
    // 2.弹出节点打印，它的右树重复1
    // 3.直到没子树且栈空
    public static void inOrder(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        pushLeftInStack(head, stack);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.print(cur.val + " ");
            if (cur.right != null) {
                pushLeftInStack(cur.right, stack);
            }
        }
    }

    public static void pushLeftInStack(Node head, Stack<Node> stack) {
        while (head != null) {
            stack.push(head);
            head = head.left;
        }
    }

    // https://leetcode.com/problems/binary-tree-postorder-traversal/
    public static void postOrder(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> in = new Stack<>();
        Stack<Node> out = new Stack<>();
        in.push(head);
        while (!in.isEmpty()) {
            Node cur = in.pop();
            out.push(cur);
            if (cur.left != null) {
                in.push(cur.left);
            }
            if (cur.right != null) {
                in.push(cur.right);
            }
        }
        while (!out.isEmpty()) {
            System.out.print(out.pop().val + " ");
        }
    }

    // https://leetcode.com/problems/binary-tree-postorder-traversal/
    public static void postOrderOneStack(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        // 如果始终没有打印过节点，h就一直是头节点
        // 一旦打印过节点，h就变成打印节点
        // 之后h的含义 : 上一次打印的节点
        while(!stack.isEmpty()) {
            Node cur = stack.peek();
            if (cur.left != null && head != cur.left && head != cur.right) {
                // 有左树且左树没处理过
                stack.push(cur.left);
            } else if (cur.right != null && head != cur.right) {
                // 有右树且右树没处理过
                stack.push(cur.right);
            } else {
                // 左树、右树 没有 或者 都处理过了
                System.out.print(cur.val + " ");
                head = stack.pop();
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
        preOrder(head);
        System.out.println("先序遍历非递归版");
        inOrder(head);
        System.out.println("中序遍历非递归版");
        postOrder(head);
        System.out.println("后序遍历非递归版 - 2个栈实现");
        postOrderOneStack(head);
        System.out.println("后序遍历非递归版 - 1个栈实现");
    }


}
