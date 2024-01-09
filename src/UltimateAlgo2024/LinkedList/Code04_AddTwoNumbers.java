package UltimateAlgo2024.LinkedList;

// https://leetcode.com/problems/add-two-numbers/
public class Code04_AddTwoNumbers {


    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    public ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        ListNode ans = null, cur = null;
        int carry = 0;

        for (int sum, val;
             head1 != null || head2 != null;
             head1 = head1 != null ? head1.next : null, head2 = head2 != null ? head2.next : null
        ) {
            sum = (head1 == null ? 0 : head1.val) +
                    (head2 == null ? 0 : head2.val) +
                    carry;
            val = sum % 10;
            carry = sum / 10;

            // construct list
            if (ans == null) {
                ans = new ListNode(val);
                cur = ans;
            } else {
                cur.next = new ListNode(val);
                cur = cur.next;
            }
        }

        cur.next = carry != 0 ? new ListNode(carry) : null;

        return ans;
    }

}
