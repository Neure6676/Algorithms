package SystematicClass.Class15;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 并查集的实现
 */
public class Code05_UnionFind {

    //包一层
     private static class Node<V> {
         V value;

         public Node(V v) {
             value = v;
         }
    }

    public static class UnionSet<V> {
         public HashMap<V, Node<V>> nodes;
         public HashMap<Node<V>, Node<V>> parents; //用一张表存"指针"
         public HashMap<Node<V>, Integer> sizeMap;//辅助实现小挂大（优化1） 只存代表节点

        public UnionSet(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value : values) {
                Node<V> node = new Node<>(value);
                nodes.put(value, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        // 给你一个节点，请你往上到不能再往上，把代表返回
        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);   // 最后的cur就是最上面的点
            }
            while (!path.isEmpty()) {    // 把这条链上的点直接挂到最上面的点，最后查谁的祖先都是一步到位（优化2）
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        //查询a和b在不在一个集合
        public boolean isSameSet(V a, V b) {
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        //a和b背后所有的点合成一个集合
        public void union(V a, V b) {
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parents.put(small, big);
                sizeMap.put(big, aSetSize + bSetSize);
                sizeMap.remove(small);
            }
        }

        public int sets() {
            return sizeMap.size();
        }

    }
}
