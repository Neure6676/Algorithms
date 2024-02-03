package UltimateAlgo2024.Graph.Part1;

import java.util.ArrayList;
import java.util.Arrays;

// https://leetcode.com/problems/alien-dictionary/
public class Code03_AlienDictionary {

    public String alienOrder(String[] words) {
        int[] indegree = new int[26];
        // 出现过的字符标0，剩下的标-1
        Arrays.fill(indegree, -1);
        for (String w : words) {
            for (int i = 0; i < w.length(); i++) {
                indegree[w.charAt(i) - 'a'] = 0;
            }
        }
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>());
        }
        // 看当前这两个String是靠哪个位置分出前后的，放入graph
        for (int i = 0, j, len; i < words.length - 1; i++) {
            String cur = words[i];
            String next = words[i + 1];
            j = 0;
            len = Math.min(cur.length(), next.length());
            for (; j < len; j++) {
                if (cur.charAt(j) != next.charAt(j)) {
                    graph.get(cur.charAt(j) - 'a').add(next.charAt(j) - 'a');
                    indegree[next.charAt(j) - 'a']++;
                    break;
                }
            }
            if (j < cur.length() && j == next.length()) {
                return "";
            }
        }
        // 拓扑排序
        int[] queue = new int[26];
        int l = 0, r = 0;
        int cnt = 0;
        for (int i = 0; i < 26; i++) {
            if (indegree[i] != -1) {
                cnt++;
            }
            if (indegree[i] == 0) {
                queue[r++] = i;
            }
        }
        StringBuilder ans = new StringBuilder();
        while (l < r) {
            int cur = queue[l++];
            ans.append((char)('a' + cur));
            for (int next : graph.get(cur)) {
                if (--indegree[next] == 0) {
                    queue[r++] = next;
                }
            }
        }
        return ans.length() == cnt ? ans.toString() : "";
    }
}
