package ForOffer.Class10;

/**
 * 给定一棵搜索二叉树头节点，转化成首尾相接的有序双向链表（节点都有两个方向的指针）
 * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 */
public class Code04_BSTtoDoubleLinkedList {

    public static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    // 二叉树递归套路
    // Info: 已经串号的双向链表头尾节点
    public static class Info {
        public Node start;
        public Node end;

        public Info(Node s, Node e) {
            start = s;
            end = e;
        }
    }

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Info allInfo = process(root);
        allInfo.start.left = allInfo.end;
        allInfo.end.right = allInfo.start;
        return allInfo.start;
    }

    public static Info process(Node X) {
        if (X == null) {
            return new Info(null, null);
        }
        Info lInfo = process(X.left);
        Info rInfo = process(X.right);
        if (lInfo.end != null) {
            lInfo.end.right = X;
        }
        // 这两行无所谓前后是不是null
        X.left = lInfo.end;
        X.right = rInfo.start;
        if (rInfo.start != null) {
            rInfo.start.left = X;
        }
        // 整体链表的头    lInfo.start != null ? lInfo.start : X
        // 整体链表的尾    rInfo.end != null ? rInfo.end : X
        return new Info(lInfo.start == null ? X : lInfo.start, rInfo.end == null ? X : rInfo.end);
    }

}
