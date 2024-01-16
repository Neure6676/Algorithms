package UltimateAlgo2024.LinkedList;

// https://leetcode.com/problems/intersection-of-two-linked-lists/description/
public class Code06_IntersectionOfTwoLinkedLists {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // 用简单几个变量（不使用容器）解决
    // 假设链表1长为100，链表2长为80
    // 先让1走20步，再一起走
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == headB) return headA;

        int lenA = 0, lenB = 0;
        ListNode curA = headA, curB = headB;
        while (curA.next != null) {
            curA = curA.next;
            lenA++;
        }
        while (curB.next != null) {
            curB = curB.next;
            lenB++;
        }
        if (curA == curB) {
            int larger = Math.max(lenA, lenB);
            int smaller = Math.min(lenA, lenB);
            ListNode largerNode = larger == lenA ? headA : headB;
            ListNode smallerNdoe = largerNode == headA ? headB : headA;
            for (int i = 0; i < larger - smaller; i++) {
                largerNode = largerNode.next;
            }
            while (smaller >= 0) {
                if (largerNode == smallerNdoe) {
                    return largerNode;
                }
                largerNode = largerNode.next;
                smallerNdoe = smallerNdoe.next;
                smaller--;
            }
        }
        return null;
    }

    public static ListNode getIntersectionNode1(ListNode h1, ListNode h2) {
        if (h1 == null || h2 == null) {
            return null;
        }
        ListNode a = h1, b = h2;
        int diff = 0;
        while (a.next != null) {
            a = a.next;
            diff++;
        }
        while (b.next != null) {
            b = b.next;
            diff--;
        }
        if (a != b) {
            return null;
        }
        if (diff >= 0) {
            a = h1;
            b = h2;
        } else {
            a = h2;
            b = h1;
        }
        diff = Math.abs(diff);
        while (diff-- != 0) {
            a = a.next;
        }
        while (a != b) {
            a = a.next;
            b = b.next;
        }
        return a;
    }
}
