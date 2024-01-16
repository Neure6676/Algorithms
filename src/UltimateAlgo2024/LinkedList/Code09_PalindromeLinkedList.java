package UltimateAlgo2024.LinkedList;

// https://leetcode.com/problems/palindrome-linked-list/
public class Code09_PalindromeLinkedList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        // if (head.next == null) {
        //     return true;
        // }
        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            cur = cur.next;
            count++;
        }
        int half = count / 2;
        ListNode pre = null, next = null;
        cur = head;
        while (half-- != 0) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        half = count / 2;
        if (2 * half != count) {
            cur = cur.next;
        }
        while (pre != null && cur != null) {
            if (cur.val != pre.val) {
                return false;
            }
            pre = pre.next == null ? null : pre.next;
            cur = cur.next == null ? null : cur.next;
        }
        return true;
    }
}
