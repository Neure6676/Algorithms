package SystematicClass.Class14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定一棵二叉树的头节点head，和另外两个节点a和b，返回a和b的最低公共祖先
 *
 * 分类：汇聚点和x无关；汇聚点和x有关
 * 1。x无关（x不是汇聚点）：左有答案；右右答案；a和b没找全
 * 2。x有关（x就是答案）：左右树各一个；x本身就是a；x本身就是b
 *
 * 1。发现a否
 * 2。发现b否
 * 3。有没有a和b汇聚的节点
 */
public class Code03_lowestAncestor {
    private static class Node {
        private int val;
        private Node left;
        private Node right;

        public Node(int _val) {
            val = _val;
        }
    }

    public static Node lowestAncestor1(Node root, Node a, Node b) {
        if (root == null) {
            return null;
        }
        HashMap<Node, Node> parentNode = new HashMap<>(); //HashMap的操作：put，get
        parentNode.put(root, null); //记得把自身也加进去
        fillparents(root, parentNode);
        Node cur = a;
        HashSet<Node> aParents = new HashSet<>();  //HashSet的操作：add，contains
        aParents.add(cur); //记得把自身也加进去
        while (parentNode.get(cur) != null) {
            cur = parentNode.get(cur); //找遍所有父节点
            aParents.add(cur);
        }
        cur = b;
        while (!aParents.contains(cur)) {
            cur = parentNode.get(cur);
        }
        return cur;
    }

    private static void fillparents(Node node, HashMap<Node, Node> parentNode) {
        if (node.left != null) {
            parentNode.put(node.left, node);
            fillparents(node.left, parentNode);
        }
        if (node.right != null) {
            parentNode.put(node.right, node);
            fillparents(node.right, parentNode);
        }
    }



    public static Node lowestAncestor2(Node root, Node a, Node b) {
        return process(root, a, b).ans;
    }

    private static class Info {
        private boolean findA;
        private boolean findB;
        private Node ans;

        public Info(boolean a, boolean b, Node _ans) {
            findA = a;
            findB = b;
            ans = _ans;
        }

    }

    private static Info process(Node x, Node a, Node b) {
        if (x == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(x.left, a, b);
        Info rightInfo = process(x.right, a, b);
        boolean findA = leftInfo.findA || rightInfo.findA || x == a;
        boolean findB = leftInfo.findB || rightInfo.findB || x == b;
        Node ans = null;
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        } else if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        } else if (findA && findB) { //如果此时又有a又有b，x就是答案
            ans = x;
        }
        return new Info(findA, findB, ans);
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

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor1(head, o1, o2) != lowestAncestor2(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
