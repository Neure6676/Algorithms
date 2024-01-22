package UltimateAlgo2024.LinkedList;

// https://leetcode.com/problems/copy-list-with-random-pointer/
public class Code08_CopyListWithRandomPointer {

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        // 1 -> 2 -> 3
        // 1 -> 1'-> 2->2' -> 3->3'
        // 1'->2'->3'
        // 1->2-> 3->null
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }
        // add random
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            next = cur.next.next == null ? null : cur.next.next;
            cur = next;
        }
        // split
        cur = head;
        Node ans = cur.next;
        while (cur != null) {
            if (cur.next.next == null) {
                cur.next = null;
                break;
            }
            Node temp = cur.next.next;
            cur.next.next = temp.next;
            cur.next = temp;
            cur = cur.next == null ? null : cur.next;
        }
        return ans;
    }
}
