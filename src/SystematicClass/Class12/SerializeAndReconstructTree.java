package SystematicClass.Class12;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化与反序列化
 */
public class SerializeAndReconstructTree {

    private static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    private static Queue<String> preSerial(Node root) {
        Queue<String> ans = new LinkedList<>();
        pres(root, ans);
        return ans;
    }

    private static void pres(Node root, Queue<String> ans) {
        if (root == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(root.value));
            pres(root.left, ans);
            pres(root.right, ans);
        }
    }

    private static Node buildByPreQueue(Queue<String> preList) {
        if (preList == null || preList.size() == 0) {
            return null;
        }
        return preb(preList);
    }

    private static Node preb(Queue<String> preList) {
        String value = preList.poll();
        if (value == null) {
            return null;
        }
        Node root = new Node(Integer.valueOf(value));
        root.left = preb(preList);
        root.right = preb(preList);
        return root;
    }

    public static Queue<String> BFSerial(Node root) {
        Queue<String> ans = new LinkedList<>(); // save serial answer
        if (root == null) {
            return null;
        } else {
            Queue<Node> queue = new LinkedList<>(); // save how many levels 辅助数组
            queue.add(root);
            while (!queue.isEmpty()) {
                Node cur = queue.poll();
                if (cur.left != null) {  // 如果当前节点的左孩子不为空 既序列化又放到队列中 否则只进行序列化
                    ans.add(String.valueOf(cur.left.value));
                    queue.add(cur.left);
                } else {
                    ans.add(null);
                }
                if (cur.right != null) {  // 如果当前节点的右孩子不为空 既序列化又放到队列中 否则只进行序列化
                    ans.add(String.valueOf(cur.right.value));
                    queue.add(cur.right);
                } else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }


    public static Node buildByLevelQueue(Queue<String> levelList) {
        if (levelList == null || levelList.size() == 0) {
            return null;
        }
        Node head = generateNode(levelList.poll());
        Queue<Node> queue = new LinkedList<Node>();
        if (head != null) {
            queue.add(head);
        }
        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNode(levelList.poll());
            node.right = generateNode(levelList.poll());
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return head;
    }

    public static Node generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new Node(Integer.valueOf(val));
    }
}
