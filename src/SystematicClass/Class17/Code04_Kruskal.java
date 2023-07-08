package SystematicClass.Class17;

import java.util.*;

// 最小生成树
// 并查集，贪心
// 方法：从小到大检查，只要不形成环就合并
// 核心：如何检查是否形成环
public class Code04_Kruskal {
    // Union-Find Set
    public static class UnionFind {
        private HashMap<Node, Node> fatherMap;
        private HashMap<Node, Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<Node, Node>();
            sizeMap = new HashMap<Node, Integer>();
        }

        public void makeSet(Collection<Node> nodes) {  // 如果使用List要强转
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        private Node findFather(Node n) {
            Stack<Node> path = new Stack<>();
            while (n != fatherMap.get(n)) {
                path.push(n);
                n = fatherMap.get(n);
            }
            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), n);
            }
            return n;
        }

        public boolean isSameSet(Node a, Node b) {   //判断是否有环的方法，看edge的两端在不在同一集合
            return findFather(a) == findFather(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node fa = findFather(a);
            Node fb = findFather(b);
            if (fa != fb) {
                if (sizeMap.get(a) <= sizeMap.get(b)) {
                    fatherMap.put(a, fb);
                    sizeMap.put(fb, sizeMap.get(fa) + sizeMap.get(fb));
                    sizeMap.remove(fa);
                } else {
                    fatherMap.put(b, fa);
                    sizeMap.put(fa, sizeMap.get(fa) + sizeMap.get(fb));
                    sizeMap.remove(fb);
                }
            }
        }

    }

    public static class edgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }



    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSet(graph.nodes.values());
        // 从小到大的边依次弹出
        PriorityQueue<Edge> queue = new PriorityQueue<>(new edgeComparator()); // 比较器前记得加new
        for (Edge edge : graph.edges) {
            queue.add(edge);
        }
        Set<Edge> res = new HashSet<>();
        while (!queue.isEmpty()) {
            Edge curEdge = queue.poll();
            if (!unionFind.isSameSet(curEdge.from, curEdge.to)) {
                res.add(curEdge);
                // 最后记得合并
                unionFind.union(curEdge.from, curEdge.to);
            }
        }
        return res;
    }
}
