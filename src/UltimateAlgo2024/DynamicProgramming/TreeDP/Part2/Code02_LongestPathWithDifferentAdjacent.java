package UltimateAlgo2024.DynamicProgramming.TreeDP.Part2;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/longest-path-with-different-adjacent-characters/
public class Code02_LongestPathWithDifferentAdjacent {

    /**
     * 1. 不通过x
     * 2. 通过x
     */
    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            graph.get(parent[i]).add(i);
        }
        Info info = f(s.toCharArray(), graph, 0);
        return info.maxPath;
    }

    public static class Info {
        int maxPathFromHead;
        int maxPath;

        public Info(int _maxPathFromHead, int _maxPath) {
            maxPathFromHead = _maxPathFromHead;
            maxPath = _maxPath;
        }
    }

    public static Info f(char[] s, List<List<Integer>> graph, int cur) {
        // cur is leaf
        if (graph.get(cur).isEmpty()) {
            return new Info(1, 1);
        }
        int firstMax = 0;
        int secondMax = 0;
        int maxPath = 1;
        for (int v : graph.get(cur)) {
            Info nextInfo = f(s, graph, v);
            maxPath = Math.max(maxPath, nextInfo.maxPath);
            if (s[cur] != s[v]) {
                if (nextInfo.maxPathFromHead > firstMax) {
                    secondMax = firstMax;
                    firstMax = nextInfo.maxPathFromHead;
                } else if (nextInfo.maxPathFromHead > secondMax){
                    secondMax = nextInfo.maxPathFromHead;
                }
            }
        }
        int maxPathFromHead = firstMax + 1;
        maxPath = Math.max(maxPath, firstMax + secondMax + 1);
        return new Info(maxPathFromHead, maxPath);
    }
}
