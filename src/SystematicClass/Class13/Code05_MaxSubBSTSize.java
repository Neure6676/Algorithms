package SystematicClass.Class13;

/**
 * 给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的大小
 * LeetCode 333
 * 分类：x是头；x不是头
 *
 */
public class Code05_MaxSubBSTSize {

      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }

      public static int maxSubBSTSize2(TreeNode root) {
          if (root == null) {
              return 0;
          }
          return process(root).maxBSTSubtreeSize;
      }

      private static class Info {
          public int maxBSTSubtreeSize; //整棵树上最大子BST的大小
          public int allSize; //整棵树的大小
          public int max;
          public int min;

          public Info(int m, int a, int _max, int _min) {
              maxBSTSubtreeSize = m;
              allSize = a;
              max = _max;
              min = _min;
          }
      }

      public static Info process(TreeNode x) {
          //base case
          if (x == null) {
              return null;
          }

          Info leftInfo = process(x.left);
          Info rightInfo = process(x.right);

          int allSize =  1;
          int max = x.val;
          int min = x.val;
          if (leftInfo != null) {
              max = Math.max(leftInfo.max, max);
              min = Math.min(leftInfo.min, min);
              allSize += leftInfo.allSize;
          }
          if (rightInfo != null) {
              max = Math.max(rightInfo.max, max);
              min = Math.min(rightInfo.min, min);
              allSize += rightInfo.allSize;
          }
          int p1 = -1;
          if (leftInfo != null) {
              p1 = leftInfo.maxBSTSubtreeSize;
          }
          int p2 = -1;
          if (rightInfo != null) {
              p2 = rightInfo.maxBSTSubtreeSize;
          }
          int p3 = -1;
          boolean leftBST = leftInfo == null ? true : (leftInfo.maxBSTSubtreeSize == leftInfo.allSize);
          boolean rightBST = rightInfo == null ? true : (rightInfo.maxBSTSubtreeSize == rightInfo.allSize);
          if (leftBST && rightBST) {
              boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.val);
              boolean rightMinMoreX = rightInfo == null ? true : (x.val < rightInfo.min);
              if (leftMaxLessX && rightMinMoreX) {
                  int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
                  int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
                  p3 = leftSize + rightSize + 1;
              }
          }
          return new Info(Math.max(p1, Math.max(p2, p3)), allSize, max, min);
      }

}
