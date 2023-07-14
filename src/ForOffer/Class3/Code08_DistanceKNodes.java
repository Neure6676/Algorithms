package ForOffer.Class3;

import java.util.*;

/**
 * 给定三个参数，二叉树的头节点head，树上某个节点target，正数K。从target开始，可以向上走或者向下走，返回与target的距离是K的所有节点
 *
 * 思路：建立父map 方便找到每个节点的父 此时相当于graph BFS
 */
public class Code08_DistanceKNodes {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static List<Node> distanceKNodes(Node root, Node target, int K) {
        // 父map
        HashMap<Node, Node> parents = new HashMap<>();
        parents.put(root, null);
        createParentMap(root, parents);
        // BFS 标记层数方法 同样距离的节点统一看作一批 统一操作
        Queue<Node> queue = new LinkedList<>();// 队列
        Set<Node> visited = new HashSet<>();// set用于记录进入过队列的
        queue.offer(target);
        visited.add(target);
        int curLevel = 0;
        List<Node> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size(); // 这一批几个节点
            while (size-- > 0) {
                Node cur = queue.poll();
                if (curLevel == K) {
                    ans.add(cur);
                }
                if (cur.left != null && !visited.contains(cur.left)) { //不为空且没访问过
                    queue.add(cur.left);
                    visited.add(cur.left);
                }
                if (cur.right != null && !visited.contains(cur.right)) {
                    queue.add(cur.right);
                    visited.add(cur.right);
                }
                if (parents.get(cur) != null && !visited.contains(parents.get(cur))) {
                    queue.add(parents.get(cur));
                    visited.add(parents.get(cur));
                }
            }
            curLevel++; //  维护结构！
            if (curLevel > K) { // 减小运算量
                break;
            }
        }
        return ans;
    }


    public static void createParentMap(Node cur, HashMap<Node, Node> parents) {
        if (cur == null) {
            return;
        }
        if (cur.left != null) {
            parents.put(cur.left, cur);
            createParentMap(cur.left, parents);
        }
        if (cur.right != null) {
            parents.put(cur.right, cur);
            createParentMap(cur.right, parents);
        }
    }

    public static void main(String[] args) {
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);

        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n2.left = n7;
        n2.right = n4;

        Node root = n3;
        Node target = n5;
        int K = 2;

        List<Node> ans = distanceKNodes(root, target, K);
        for (Node o1 : ans) {
            System.out.println(o1.value);
        }

    }


}
