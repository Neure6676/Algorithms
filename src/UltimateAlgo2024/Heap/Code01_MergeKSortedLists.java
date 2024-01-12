package UltimateAlgo2024.Heap;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/merge-k-sorted-lists/description/
public class Code01_MergeKSortedLists {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static class NodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        // PriorityQueue<ListNode> heap = new PriorityQueue<>(new NodeComparator());
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode node : lists) {
            if (node != null) {
                heap.add(node);
            }
        }
        ListNode ans = null, last = null;
        while (!heap.isEmpty()) {
            ListNode cur = heap.poll();
            if (ans == null) {
                ans = cur;
                last = cur;
            } else {
                last.next = cur;
                last = last.next;
            }
            if (cur.next != null) {
                heap.add(cur.next);
            }
        }
        return ans;
    }
}
