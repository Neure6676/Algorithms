package UltimateAlgo2024.KMP;

// https://leetcode.com/problems/linked-list-in-binary-tree/
public class Code03_LinkedListInBinaryTree {


      public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }


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

    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head != null && root != null) {
            return f(head, root) || isSubPath(head, root.right) || isSubPath(head, root.left);
        }
        return head == null;
    }

    public static boolean f(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        return (head.val == root.val) && (f(head.next, root.left) || f(head.next, root.right));
    }



}
