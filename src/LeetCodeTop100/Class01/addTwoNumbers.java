package LeetCodeTop100.Class01;

public class addTwoNumbers {

    public class ListNode {
      int val;
      ListNode next;

      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    /**
     * Input: l1 = [2,4,3], l2 = [5,6,4]
     * Output: [7,0,8]
     * Explanation: 342 + 465 = 807.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // 不能转成数字相加 因为int和long长度范围有限
        int ca = 0; // carry进位
        int n1 = 0; // l1当前位置的值
        int n2 = 0; // l2当前位置的值
        int n = 0; // 当前Node的值
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode pre = null;
        ListNode cur = null;
        while (p1 != null || p2 != null) { // 可能不一样长
            n1 = p1 != null ? p1.val : 0;
            n2 = p2 != null ? p2.val : 0;
            n = n1 + n2 + ca;
            pre = cur;
            cur = new ListNode(n % 10);  // n可能有进位
            cur.next = pre;
            ca = n / 10;
            p1 = p1 != null ? p1.next : null;
            p2 = p2 != null ? p2.next : null;
        }
        // 最后如果还有进位 生成一个新Node
        if (ca == 1) {
            pre = cur;
            cur = new ListNode(1);
            cur.next = pre;
        }
        // cur is the last Node
        return reverseList(cur);
    }

    // null <-  1 <-  2  <- 3 <- 4
    //                              head
    //                          pre  cur
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = null;
        while (head != null) {
            cur = head.next;  // 移
            head.next = pre;  // 转
            pre = head; // 移
            head = cur; // 移
        }
        return pre;
    }

}
