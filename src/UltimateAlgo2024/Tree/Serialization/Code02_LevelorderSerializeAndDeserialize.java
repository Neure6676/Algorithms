package UltimateAlgo2024.Tree.Serialization;

// 二叉树层次序列化和反序列化
// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
public class Code02_LevelorderSerializeAndDeserialize {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }

    }

    public static int MAXN = 10001;

    public static TreeNode[] queue = new TreeNode[MAXN];

    public static int l, r;

    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        if (root != null) {
            builder.append(root.val + ",");
            l = 0;
            r = 0;
            queue[r++] = root;
            while (l < r) {
                root = queue[l++];
                if (root.left != null) {
                    builder.append(root.left.val + ",");
                    queue[r++] = root.left;
                } else {
                    builder.append("#,");
                }
                if (root.right != null) {
                    builder.append(root.right.val + ",");
                    queue[r++] = root.right;
                } else {
                    builder.append("#,");
                }
            }
        }
        return builder.toString();
    }

    public TreeNode deserialize(String data) {
        if (data.equals("")) {
            return null;
        }
        String[] nodes = data.split(",");
        int index = 0;
        TreeNode root = generate(nodes[index++]);
        l = 0;
        r = 0;
        queue[r++] = root;
        while (l < r) {
            TreeNode cur = queue[l++];
            cur.left = generate(nodes[index++]);
            cur.right = generate(nodes[index++]);
            if (cur.left != null) {
                queue[r++] = cur.left;
            }
            if (cur.right != null) {
                queue[r++] = cur.right;
            }
        }
        return root;
    }

    private TreeNode generate(String val) {
        return val.equals("#") ? null : new TreeNode(Integer.valueOf(val));
    }
}
