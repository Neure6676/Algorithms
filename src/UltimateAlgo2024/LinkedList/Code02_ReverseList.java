package UltimateAlgo2024.LinkedList;

public class Code02_ReverseList {


     public class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next;}

     }


     // 移 转 移 移
    public ListNode reverseSinglyLinkedList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            //  1 2 3 4
            //p h n
            //  p h n
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }


    public class DoubleListNode {
         int val;
         DoubleListNode pre;
         DoubleListNode next;

         public DoubleListNode(int _val) {
             val = _val;
             pre = null;
             next = null;
         }
    }

    public static DoubleListNode reverseDoublyLinkedList(DoubleListNode head) {
         DoubleListNode pre = null;
         DoubleListNode next = null;
         while (head != null) {
             next = head.next;
             head.pre = next;
             head.next = pre;
             pre = head;
             head = next;
         }
         return pre;
    }



}
