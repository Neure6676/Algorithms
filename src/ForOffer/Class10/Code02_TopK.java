package ForOffer.Class10;

import java.util.*;

/**
 * Top K Frequent Words II
 * Implement three methods for Topk Class:
 * TopK(k). The constructor.
 * add(word). Add a new word.
 * topk(). Get the current top k frequent words.
 * LintCode题目：https://www.lintcode.com/problem/550/
 */
public class Code02_TopK {

    // 加强堆：通过一个反向表找到它之前在哪
    // 用小根堆：topK中出现次数最小的放在0位置，当守门员
    // 步骤：1.先更新词频表 2。如果已经在heap中，更新位置heapify（下沉）；
    public static class Node {
        public String str;
        public int times;

        public Node(String s, int t) {
            str = s;
            times = t;
        }
    }

    private Node[] heap;
    private int heapSize;
    // 词频表
    private HashMap<String, Node> strNodeMap;
    //反向表 val是该str在heap中的位置 -1说明不在
    private HashMap<Node, Integer> nodeIndexMap;
    // heap比较器
    private NodeHeapComp comp;
    private TreeSet<Node> treeSet;

    public static class NodeHeapComp implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.times - o2.times;
        }
    }

    public static class NodeTreeSetComp implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.times != o2.times ? (o2.times - o1.times) : (o1.str.compareTo(o2.str));
        }

    }

    public Code02_TopK(int k) {
        heap = new Node[k];
        heapSize = 0;
        strNodeMap = new HashMap<String, Node>();
        nodeIndexMap = new HashMap<Node, Integer>();
        comp = new NodeHeapComp();
        treeSet = new TreeSet<>(new NodeTreeSetComp());
    }

    public void add(String str) {
        if (heap.length == 0) {
            return;
        }
        // str对应的节点
        Node curNode = null;
        // curNode在堆上的位置
        int preIndex = -1;
        if (!strNodeMap.containsKey(str)) {
            curNode = new Node(str, 1);
            strNodeMap.put(str, curNode);
            nodeIndexMap.put(curNode, -1);
        } else {
            curNode = strNodeMap.get(str);
            curNode.times++;
            // 要在time++之前，先在treeSet中删掉
            // 原因是因为一但times++，curNode在treeSet中的排序就失效了
            // 这种失效会导致整棵treeSet出现问题
            if (treeSet.contains(curNode)) {
                treeSet.remove(curNode);
            }
            // 可能已经有preIndex了
            preIndex = nodeIndexMap.get(curNode);
        }
        if (preIndex == -1) {
            //1.heap满了 2。heap没满
            if (heapSize == heap.length) {
                if (comp.compare(heap[0], curNode) < 0) {
                    treeSet.remove(heap[0]);
                    treeSet.add(curNode);
                    nodeIndexMap.put(heap[0], -1);  // 替换以前heap顶部的
                    nodeIndexMap.put(curNode, 0);
                    heap[0] = curNode;
                    heapify(0, heapSize);
                }
            } else {
                treeSet.add(curNode);
                nodeIndexMap.put(curNode, heapSize);
                heap[heapSize] = curNode;
                heapInsert(heapSize++);
            }
        } else {
            treeSet.add(curNode);
            heapify(preIndex, heapSize);
        }
    }

    public List<String> topk() {
        ArrayList<String> ans = new ArrayList<>();
        for (Node node : treeSet) {
            ans.add(node.str);
        }
        return ans;
    }

    private void heapInsert(int index) {
        while (index != 0) {
            int parent = (index - 1) / 2;
            if (comp.compare(heap[index], heap[parent]) < 0) {
                swap(parent, index);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void heapify(int index, int heapSize) {
        int l = index * 2 + 1;
        int r = index * 2 + 2;
        int smallest = index;
        while (l < heapSize) {
            if (comp.compare(heap[l], heap[index]) < 0) {
                smallest = l;
            }
            if (r < heapSize && comp.compare(heap[r], heap[smallest]) < 0) {
                smallest = r;
            }
            if (smallest != index) {
                swap(smallest, index);
            } else {
                break;
            }
            index = smallest;
            l = index * 2 + 1;
            r = index * 2 + 2;
        }
    }

    private void swap(int index1, int index2) {
        nodeIndexMap.put(heap[index1], index2);
        nodeIndexMap.put(heap[index2], index1);
        Node tmp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = tmp;
    }


}
