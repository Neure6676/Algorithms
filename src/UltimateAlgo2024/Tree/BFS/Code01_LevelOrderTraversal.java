package UltimateAlgo2024.Tree.BFS;

import java.util.*;

// https://leetcode.com/problems/binary-tree-level-order-traversal/
public class Code01_LevelOrderTraversal {

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

      // 经典版
    public List<List<Integer>> levelOrder0(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root != null) {
            Queue<TreeNode> queue = new LinkedList<>();
            // 每个节点在第几层
            HashMap<TreeNode, Integer> map = new HashMap<>();
            queue.add(root);
            map.put(root, 0);
            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                int level = map.get(cur);
                if (ans.size() == level) {
                    // 需要新建链表
                    ans.add(new ArrayList<>());
                }
                ans.get(level).add(cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);
                    map.put(cur.left, level + 1);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                    map.put(cur.right, level + 1);
                }
            }
        }
        return ans;
    }


    // 改进版
    // 一次处理一层
    // 1. 拿此时队列长度size
    // 2. 如下行为重复size遍：弹出，左加，右加

    public static TreeNode[] queue = new TreeNode[2001];

      public static int l;

      public static int r; // [l, r)

    public static int size;


    public List<List<Integer>> levelOrder(TreeNode root) {
          List<List<Integer>> ans = new ArrayList<>();
          if (root != null) {
              r = l =0;
              queue[r++] = root;
              while (l < r) {  // queue != null
                  List<Integer> thisLevel = new ArrayList<>();
                  size = r - l;
                  for (int i = 0; i < size; i++) {
                      TreeNode cur = queue[l++];
                      thisLevel.add(cur.val);
                      if (cur.left != null) {
                          queue[r++] = cur.left;
                      }
                      if (cur.right != null) {
                          queue[r++] = cur.right;
                      }
                  }
                  ans.add(thisLevel);
              }
          }
          return ans;
    }



}
