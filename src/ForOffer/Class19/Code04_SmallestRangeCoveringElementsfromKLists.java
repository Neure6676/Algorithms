package ForOffer.Class19;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

// 本题测试链接 : https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
public class Code04_SmallestRangeCoveringElementsfromKLists {

    /**
     * Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
     * Output: [20,24]
     */
    public int[] smallestRange(List<List<Integer>> nums) {
        /**
         * 有序表
         * 先各放一个最小值 生成一个可行区间
         * 再遍历表中最小的 递归这个区间
         */
        int N = nums.size();
        TreeSet<Node> set = new TreeSet<>(new NodeComparator());
        for (int i = 0; i < N; i++) {
            set.add(new Node(nums.get(i).get(0), i, 0));
        }
        boolean key = false;
        int a = 0;
        int b = 0;
        while (set.size() == N) {
            Node min = set.first();
            Node max = set.last();
            if (!key ||max.val - min.val < b - a){
                key = true;
                a = min.val;
                b = max.val;
            }
            min = set.pollFirst();
            int list = min.list;
            int index = min.index + 1;
            if (index != nums.get(list).size()) {
                set.add(new Node(nums.get(list).get(index), list, index));
            }
        }
        return new int[] {a, b};
    }

    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.val == o2.val ? o1.list - o2.list : o1.val - o2.val;
        }

    }



    public static class Node {
        public int val;
        public int list; //该数在第几个list
        public int index; //该数在其所在的list中的index

        public Node(int v, int l, int i) {
            val = v;
            list = l;
            index = i;
        }

    }

}
