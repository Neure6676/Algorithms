package UltimateAlgo2024.Tree.Serialization;

// 二叉树先序序列化和反序列化
// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

public class Code01_PreorderSerializeAndDeserialize {

    /**
     * Serialization is the process of converting a data structure or object into a
     * sequence of bits so that it can be stored in a file or memory buffer, or
     * transmitted across a network connection link to be reconstructed later in the
     * same or another computer environment.
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }

    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        process(root, builder);
        return builder.toString();
    }

    void process(TreeNode root, StringBuilder builder) {
        if (root == null) {
            builder.append("#,");
        } else {
            builder.append(root.val + ",");
            process(root.left, builder);
            process(root.right, builder);
        }

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] vals = data.split(",");
        count = 0;
        return process2(vals);
    }

    // 当前消费到哪了
    public static int count;

    TreeNode process2(String[] vals) {
        String cur = vals[count++];
        if (cur.equals("#")) {
            return null;
        } else {
            TreeNode head = new TreeNode(Integer.valueOf(cur));
            head.left = process2(vals);
            head.right = process2(vals);
            return head;
        }
    }
}
