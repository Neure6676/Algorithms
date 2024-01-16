package UltimateAlgo2024.LinkedList;

// https://leetcode.com/problems/reverse-nodes-in-k-group/
public class Code07_ReverseNodesInkGroup {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    //               lt     ctn
    //Input: head = [1,2, 3, 4,  5], k = 3
    //Output: [3,2,1,4,5]
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 0) {
            return null;
        }
        int len = 0;
        ListNode cnt = head;
        while(cnt.next != null) {
            cnt = cnt.next;
            len++;   // 4
        }
        int group = len / k; // 1
        ListNode curHead = head, curTail = head, ans = null, curTailNext = null, lastTail = null;
        for (int i = 0; i < group; i++) {
            int idx = k;
            while (--idx != 0) {
                curTail = curTail.next;
            }
            if (lastTail != null) {
                lastTail.next = curTail;
            }
            if (ans == null) {
                ans = curTail;
            }
            curTailNext = curTail.next;
            ListNode pre = null, next = null, cur = curHead;
            while (cur != curTailNext) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            lastTail = curHead;  // 1
            curHead = curTailNext;
            curTail = curTailNext;
        }
        lastTail.next = curTailNext;
        return ans;
    }

    public static ListNode reverseKGroup1(ListNode head, int k) {
        ListNode start = head;
        ListNode end = teamEnd(start, k);
        if (end == null) {
            return head;
        }
        // 第一组很特殊因为牵扯到换头的问题
        head = end;
        reverse(start, end);
        // 翻转之后start变成了上一组的结尾节点
        ListNode lastTeamEnd = start;
        while (lastTeamEnd.next != null) {
            start = lastTeamEnd.next;
            end = teamEnd(start, k);
            if (end == null) {
                return head;
            }
            reverse(start, end);
            lastTeamEnd.next = end;
            lastTeamEnd = start;
        }
        return head;
    }

    // 当前组的开始节点是s，往下数k个找到当前组的结束节点返回
    public static ListNode teamEnd(ListNode s, int k) {
        while (--k != 0 && s != null) {
            s = s.next;
        }
        return s;
    }

    // s -> a -> b -> c -> e -> 下一组的开始节点
    // 上面的链表通过如下的reverse方法调整成 : e -> c -> b -> a -> s -> 下一组的开始节点
    public static void reverse(ListNode s, ListNode e) {
        e = e.next;
        ListNode pre = null, cur = s, next = null;
        while (cur != e) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        s.next = e;
    }
}
