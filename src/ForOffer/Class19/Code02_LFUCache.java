package ForOffer.Class19;

import java.util.HashMap;

// 本题测试链接 : https://leetcode.com/problems/lfu-cache/
// 提交时把类名和构造方法名改为 : LFUCache
public class Code02_LFUCache {

    /**
     * 同样词频的东西只会进一个桶
     * 两张表：
     * put(1,3)
     * map1： key:1 value:Node(1,3)
     * map2： key：Node val：Bucket（桶也是双向链表 桶里的Node也是双向链表）
     */
    class LFUCache {

        public int capacity;
        public int size;
        public HashMap<Integer, Node> keyNodeMap;
        public HashMap<Node, NodeList> nodeBucketMap;
        public NodeList headList;  // 整个结构中位于最左的桶


        public LFUCache(int capacity) {
            this.capacity = capacity;
            size = 0;
            keyNodeMap = new HashMap<Integer, Node>();
            nodeBucketMap = new HashMap<Node, NodeList>();
            headList = null;
        }

        public int get(int key) {
            if (!keyNodeMap.containsKey(key)) {
                return -1;
            }
            Node node = keyNodeMap.get(key);
            node.times++;
            NodeList curNodeList = nodeBucketMap.get(node);
            move(node, curNodeList);
            return node.val;
        }

        public void put(int key, int value) {
            if (capacity == 0) {
                return;
            }
            if (keyNodeMap.containsKey(key)) {
                Node node = keyNodeMap.get(key);
                node.val = value;
                node.times++;
                NodeList curNodeList = nodeBucketMap.get(node);
                move(node, curNodeList);
            } else {
                if (size == capacity) {
                    // 删掉最旧的
                    Node node = headList.tail;
                    headList.deleteNode(node);
                    modifyHeadList(headList);
                    keyNodeMap.remove(node.key);
                    nodeBucketMap.remove(node);
                    size--;
                }
                Node node = new Node(key, value, 1);
                if (headList == null) {
                    headList = new NodeList(node);
                } else {
                    if (headList.head.times.equals(node.times)) {
                        headList.addNodeFromHead(node);
                    } else {
                        NodeList newHead = new NodeList(node);
                        newHead.next = headList;
                        headList.last = newHead;
                        headList = newHead;
                    }
                }
                keyNodeMap.put(key, node);
                nodeBucketMap.put(node, headList);
                size++;
            }
        }

        public static class Node {
            private int key;
            private int val;
            private Integer times;
            public Node last;
            public Node next;

            public Node(int k, int v, int t) {
                key = k;
                val = v;
                times = t;
            }
        }

        // 桶结构
        public static class NodeList {
            public Node head;
            public Node tail;
            public NodeList last; // 桶之间是双向链表所以有前一个桶
            public NodeList next; // 桶之间是双向链表所以有后一个桶

            public NodeList(Node node) {
                head = node;
                tail = node;
            }

            // 把一个新的节点加入这个桶，新的节点都放在顶端变成新的头部
            public void addNodeFromHead(Node newHead) {
                newHead.next = head;
                head.last = newHead;
                head = newHead;
            }

            // 判断这个桶是不是空的
            public boolean isEmpty() {
                return head == null;
            }

            public void deleteNode(Node node) {
                if (head == tail) {
                    head = null;
                    tail = null;
                    return;
                } else {
                    if (node == head) {
                        head = node.next;
                        head.last = null;
                    } else if(node == tail){
                        tail = tail.last;
                        tail.next = null;
                    } else {
                        node.last.next = node.next;
                        node.next.last = node.last;
                    }
                }
                node.last = null;
                node.next = null;
            }
        }

        // removeNodeList：刚刚减少了一个节点的桶
        // 这个函数的功能是，判断刚刚减少了一个节点的桶是不是已经空了。
        // 1）如果不空，什么也不做
        //
        // 2)如果空了，removeNodeList还是整个缓存结构最左的桶(headList)。
        // 删掉这个桶的同时也要让最左的桶变成removeNodeList的下一个。
        //
        // 3)如果空了，removeNodeList不是整个缓存结构最左的桶(headList)。
        // 把这个桶删除，并保证上一个的桶和下一个桶之间还是双向链表的连接方式
        //
        // 函数的返回值表示刚刚减少了一个节点的桶是不是已经空了，空了返回true；不空返回false
        private boolean modifyHeadList(NodeList removeNodeList) {
            if (removeNodeList.isEmpty()) {
                if (headList == removeNodeList) {
                    headList = removeNodeList.next;
                    if (headList != null) {
                        headList.last = null;
                    }
                } else {
                    removeNodeList.last.next = removeNodeList.next;
                    if (removeNodeList.next != null) {
                        removeNodeList.next.last = removeNodeList.last;
                    }
                }
                return true;
            }
            return false;
        }


        // 函数的功能
        // node这个节点的次数+1了，这个节点原来在oldNodeList里。
        // 把node从oldNodeList删掉，然后放到次数+1的桶中
        // 整个过程既要保证桶之间仍然是双向链表，也要保证节点之间仍然是双向链表
        private void move(Node node, NodeList oldNodeList) {
            oldNodeList.deleteNode(node);
            // preList表示次数+1的桶的前一个桶是谁
            // 如果oldNodeList删掉node之后还有节点，oldNodeList就是次数+1的桶的前一个桶
            // 如果oldNodeList删掉node之后空了，oldNodeList是需要删除的，所以次数+1的桶的前一个桶，是oldNodeList的前一个
            NodeList preList = modifyHeadList(oldNodeList) ? oldNodeList.last : oldNodeList;
            // nextList表示次数+1的桶的后一个桶是谁
            NodeList nextList = oldNodeList.next;
            if (nextList == null) {
                NodeList newList = new NodeList(node);
                if (preList != null) {
                    preList.next = newList;
                }
                newList.last = preList;
                if (headList == null) {
                    headList = newList;
                }
                nodeBucketMap.put(node, newList);
            } else {
                if (nextList.head.times.equals(node.times)) {
                    nextList.addNodeFromHead(node);
                    nodeBucketMap.put(node, nextList);
                } else {
                    NodeList newList = new NodeList(node);
                    if (preList != null) {
                        preList.next = newList;
                    }
                    newList.last = preList;
                    newList.next = nextList;
                    nextList.last = newList;
                    if (headList == nextList) {
                        headList = newList;
                    }
                    nodeBucketMap.put(node, newList);
                }
            }
        }


    }
}
