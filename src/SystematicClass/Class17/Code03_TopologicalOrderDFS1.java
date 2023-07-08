package SystematicClass.Class17;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;


/**
 * 拓扑序：若从x出发经最大深度是100，y出发经最大深度是80，则x<=y
 */


// OJ链接：https://www.lintcode.com/problem/topological-sorting
public class Code03_TopologicalOrderDFS1 {

    // 不要提交这个类
    public static class DirectedGraphNode {  // 临接表法
        public int label;  //value
        public ArrayList<DirectedGraphNode> neighbors;  // nexts

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    // 提交下面的
    public static class Record {
        public DirectedGraphNode node;
        public int deep;

        public Record(DirectedGraphNode n, int o) {
            node = n;
            deep = o;
        }
    }

    public static class MyComparator implements Comparator<Record> {

        @Override
        public int compare(Record o1, Record o2) {    // 若返回负数，认为第一个参数在前；若返回负数，认为第二个参数在前
            return o2.deep - o1.deep;
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        for (DirectedGraphNode cur : graph) {
            f(cur, order);
        }
        ArrayList<Record> recordArr = new ArrayList<>();
        for (Record r : order.values()) {
            recordArr.add(r);
        }
        recordArr.sort(new MyComparator());
        ArrayList<DirectedGraphNode> ans = new ArrayList<DirectedGraphNode>();
        for (Record r : recordArr) {
            ans.add(r.node);
        }
        return ans;
    }

    public static Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
        if (order.containsKey(cur)) {
            return order.get(cur);
        }
        int follow = 0;
        for (DirectedGraphNode next : cur.neighbors) {
            follow = Math.max(follow, f(next, order).deep);
        }
        Record ans = new Record(cur, follow + 1);
        order.put(cur, ans);
        return ans;
    }

}
