package UltimateAlgo2024.LinkedList;

public class ReverseList {

    public class Node {
        int val;
        Node next;

        public Node(int _val) {
            val = _val;
        }
    }

    public Node reverseSinglyLinkedList(Node head) {
        if (head == null) {
            return null;
        }

        return new Node(0);
    }
}
