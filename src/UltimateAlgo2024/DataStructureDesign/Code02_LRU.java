package UltimateAlgo2024.DataStructureDesign;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/lru-cache/
// HashMap + DoublyLinkedList
public class Code02_LRU {

    class LRUCache {

        class Node {
            int key;
            int val;
            Node last;
            Node next;

            public Node(int _key, int _val) {
                key = _key;
                val = _val;
            }
        }

        class MyLinkedList {
            Node head;
            Node tail;

            public MyLinkedList() {
                head = null;
                tail = null;
            }

            public void addNode(int k, int v) {
                Node n = new Node(k, v);
                if (head == null) {
                    head = n;
                    tail = n;
                } else {
                    tail.last = n;
                    n.next = tail;
                    tail = n;
                }
            }

            public void moveNodeToTail(Node n) {
                if (tail == n) {
                    return;
                }
                if (head == n) {
                    head.last.next = null;
                    head = head.last;
                } else {
                    n.next.last = n.last;
                    n.last.next = n.next;
                }
                list.tail.last = n;
                n.next = list.tail;
                list.tail = n;
            }

            public Node removeHead() {
                if (list.head == null) {
                    return null;
                }
                Node ans = head;
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    head.last.next = null;
                    head = head.last;
                }
                return ans;
            }
        }

        int cap;
        HashMap<Integer, Node> map;
        MyLinkedList list;



        public LRUCache(int capacity) {
            cap = capacity;
            list = new MyLinkedList();
            map = new HashMap<>();
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            Node n = map.get(key);
            list.moveNodeToTail(n);
            return n.val;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                Node n = map.get(key);
                n.val = value;
                list.moveNodeToTail(n);
            } else {
                list.addNode(key, value);
                map.put(key, list.tail);
                if (map.size() > cap) {
                    map.remove(list.removeHead().key);
                }
            }
        }
    }

}
