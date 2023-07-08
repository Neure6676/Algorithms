package SystematicClass.Class4;

public class ReverseList {
    public class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

        public class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            this.value = data;
        }
    }

    // 必须返回新head 如果head的位置不变 JVM会自动释放掉这些内存
    // head = reverseLinkedList(head)
    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null; // next的作用就是存后面的
        while (head != null) {
            next = head.next; // 把head.next先存起来，否则会丢失。
            // 运行到这里就变成pre head next， head替换了current
            head.next = pre;  // 让head指向前一个 （第一次是让第一个指向空）
            // 这两句就是移动 pre和head分别移到下面一个
            pre = head;
            head = next;
        }
        return pre;
    }

    public static DoubleNode reverseLinkedListDoubleNode(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }




}
