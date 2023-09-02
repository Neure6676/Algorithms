package ForOffer.Class19;

import java.util.HashMap;

// 本题测试链接 : https://leetcode.com/problems/lru-cache/
public class Code01_LRUCache {

    /**
     * put(1,3)
     * map key:1 value:LinkedList Node(1,3)
     * 用双向链表表示时间 越靠尾巴越近 越靠头部越远
     * 每次更新将更新后的节点改到最后
     */
    class LRUCache {



        public LRUCache(int capacity) {

        }

        public int get(int key) {

        }

        public void put(int key, int value) {

        }

        public static class Node<K, V> {
            public K key;
            public V value;
            public Node<K, V> last;
            public Node<K, V> next;

            public Node(K k, V v) {
                key = k;
                value = v;
            }
        }

        public static class NodeDoubleLinkedList<K, V> {
            private Node<K, V> head;
            private Node<K, V> tail;

            public NodeDoubleLinkedList() {
                head = null;
                tail = null;
            }

            // 来一个新Node 挂到最后
            public void addNode(Node<K, V> newNode) {
                if (newNode == null) {
                    return;
                }
                if (head == null) {
                    head = newNode;
                    tail = newNode;
                } else {
                    tail.next = newNode;
                    newNode.last = tail;
                    tail = newNode;
                }
            }

            // node原始的位置，左右重新连好，然后把node分离出来
            // 挂到整个链表的尾巴上
            public void moveNodeToTail(Node<K, V> node) {
                if (tail == node) {
                    return;
                }
                if (head == node) {
                    head = node.next;
                    head.last = null;
                } else {
                    node.next.last = node.last;
                    node.last.next = node.next;
                }
                tail.next = node;
                node.last = tail;
                node.next = null;
                tail = node;
            }

            public Node<K, V> removeHead() {
                if (head == null) {
                    return head;
                }
                Node<K, V> res = head;
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    head = res.next;
                    res.next = null;
                    head.last = null;
                }
                return res;
            }
        }

        public static class MyCache<K, V> {
            private HashMap<K, Node<K, V>> keyNodeMap;
            private NodeDoubleLinkedList<K, V> nodeList;
            private final int capacity;

            public MyCache(int cap) {
                keyNodeMap = new HashMap<K, Node<K, V>>();
                nodeList = new NodeDoubleLinkedList<K, V>();
                capacity = cap;
            }


        }

    }


}
