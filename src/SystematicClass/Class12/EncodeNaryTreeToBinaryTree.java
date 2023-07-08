package SystematicClass.Class12;


import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 431
 * N叉树如何通过二叉树来序列化、并完成反序列化
 *
 * 思路：
 * 原N叉树的root的所有孩子放到二叉树中root左树的右边界上
 */
public class EncodeNaryTreeToBinaryTree {

// Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
    }

    class Codec {
        // Encodes an n-ary tree to a binary tree.
        public TreeNode encode(Node root) {
            if (root == null) {
                return null;
            }
            TreeNode head = new TreeNode(root.val); // 多叉树的root一定也是二叉树的root
            head.left = en(root.children);
            return head;
        }

        private TreeNode en(List<Node> children) {
            TreeNode head = null;
            TreeNode cur = null;
            for (Node child : children) {
                TreeNode tNode = new TreeNode(child.val);
                if (head == null ){ // 第一次将二叉树的head设为第一个孩子
                    head = tNode;
                } else {
                    cur.right = tNode;
                }
                cur = tNode;
                cur.left = en(child.children); // 深度优先
            }
            return head;
        }

        // Decodes your binary tree to an n-ary tree.
        public Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }
            return new Node(root.val, de(root.left));
        }

        private List<Node> de(TreeNode root) {
            // 先把自己的孩子们搞定， 再和兄弟们汇合。因为encode时是深度优先
            List<Node> children = new ArrayList<>();
            while (root != null) {
                Node cur = new Node(root.val, de(root.left));
                children.add(cur);
                root = root.right;
            }
            return children;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(root));
}
