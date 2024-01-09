package UltimateAlgo2024.LinkedList;

// https://leetcode.com/problems/partition-list/
public class Code05_PartitionList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    //                  gh           l   gc
    //Input: head = [1,  4,     3,   2,  5,  2], x = 3
    //Output: [1,2,2,4,3,5]
    public ListNode partition(ListNode head, int x) {
        ListNode next = null,
                greaterOrEqualHead = null,
                greaterOrEqualCur = null,
                lessHead = null,
                lessTail = null;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.val >= x) {
                if (greaterOrEqualHead == null) {
                    greaterOrEqualHead = head;
                    greaterOrEqualCur = head;
                } else {
                    greaterOrEqualCur.next = head;
                    greaterOrEqualCur = greaterOrEqualCur.next;
                }
            } else {
                if (lessHead == null) {
                    lessHead = head;
                    lessTail = head;
                } else {
                    lessTail.next = head;
                    lessTail = lessTail.next;
                }
            }
            head = next;
        }

        if (lessTail == null) {
            return greaterOrEqualHead;
        }
        lessTail.next = greaterOrEqualHead;
        return lessHead;
    }

}
