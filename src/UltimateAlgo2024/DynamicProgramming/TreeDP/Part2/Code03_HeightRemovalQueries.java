package UltimateAlgo2024.DynamicProgramming.TreeDP.Part2;

// https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/
public class Code03_HeightRemovalQueries {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static final int MAXN = 100010;

    // 下标为节点的值
    // 即每个值对应的dfn序号
    public static int[] dfn = new int[MAXN];

    // 下标为dfn序号
    public static int[] deep = new int[MAXN];

    // 下标为dfn序号
    public static int[] size = new int[MAXN];

    // 下标0到i最大值
    public static int[] maxl = new int[MAXN];

    // 下标n-1到i最大值
    public static int[] maxr = new int[MAXN];

    // 用于分配dfn序号
    public static int dfnCnt;

    public static int[] treeQueries(TreeNode root, int[] queries) {
        dfnCnt = 0;
        f(root, 0);
        for (int i = 1; i <= dfnCnt; i++) {
            maxl[i] = Math.max(maxl[i - 1], deep[i]);
        }
        maxr[dfnCnt + 1] = 0;
        for (int i = dfnCnt; i >= 1; i--) {
            maxr[i] = Math.max(maxr[i + 1], deep[i]);
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int leftMax = maxl[dfn[queries[i]] - 1];
            int rightMax = maxr[dfn[queries[i]] + size[dfn[queries[i]]]];
            ans[i] = Math.max(leftMax, rightMax);
        }
        return ans;
    }

    // 来到x节点，从头到x经过了k条边
    public static void f(TreeNode x, int k) {
        int i = ++dfnCnt;
        dfn[x.val] = i;
        deep[i] = k;
        size[i] = 1;
        if (x.left != null) {
            f(x.left, k + 1);
            size[i] += size[dfn[x.left.val]];
        }
        if (x.right != null) {
            f(x.right, k + 1);
            size[i] += size[dfn[x.right.val]];
        }
    }
}
