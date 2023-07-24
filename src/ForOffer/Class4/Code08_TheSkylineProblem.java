package ForOffer.Class4;

import java.util.*;

/**
 * 大楼轮廓线问题
 * Leetcode题目：https://leetcode.com/problems/the-skyline-problem/
 */
public class Code08_TheSkylineProblem {

    // 轮廓线的产生只来自最大高度
    public static class Node {
        public int x;
        public boolean isAdd;
        public int h;

        public Node(int x, boolean isAdd, int h) {
            this.x = x;
            this.isAdd = isAdd;
            this.h = h;
        }
    }

    public static class MyComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.x - o2.x;
        }
    }

    public List<List<Integer>> getSkyline(int[][] matrix) {
        Node[] nodes = new Node[matrix.length * 2];
        for (int i = 0; i < matrix.length; i++) {
            nodes[i * 2] = new Node(matrix[i][0], true, matrix[i][2]);
            nodes[i * 2 + 1] = new Node(matrix[i][1], false, matrix[i][2]);
        }
        Arrays.sort(nodes, new MyComparator());
        // key  高度  value 次数
        TreeMap<Integer, Integer> mapHeightTimes = new TreeMap<>();
        // 每个位置x的最大高度表
        TreeMap<Integer, Integer> mapXHeight = new TreeMap<>();
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].isAdd) {
                if (!mapHeightTimes.containsKey(nodes[i].h)) {
                    mapHeightTimes.put(nodes[i].h, 1);
                } else {
                    mapHeightTimes.put(nodes[i].h, mapHeightTimes.get(nodes[i].h) + 1);
                }
            } else { // 当前Node是减
                if (mapHeightTimes.get(nodes[i].h) == 1) {
                    mapHeightTimes.remove(nodes[i].h);
                } else {
                    mapHeightTimes.put(nodes[i].h, mapHeightTimes.get(nodes[i].h) - 1);
                }
            }
            // 维护mapXHeight
            if (mapHeightTimes.isEmpty()) {
                mapXHeight.put(nodes[i].x, 0);
            } else {
                mapXHeight.put(nodes[i].x, mapHeightTimes.lastKey());
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : mapXHeight.entrySet()) {
            int curX = entry.getKey();
            int curMaxHeight = entry.getValue();
            if (ans.isEmpty() || ans.get(ans.size() - 1).get(1) != curMaxHeight) {
                ans.add(new ArrayList<>(Arrays.asList(curX, curMaxHeight)));
            }
        }
        return ans;
    }

}
