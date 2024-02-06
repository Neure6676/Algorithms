package UltimateAlgo2024.Graph.Part4BFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

// https://leetcode.com/problems/stickers-to-spell-word/
public class Code02_StickersToSpellWord {

    public static int MAXN = 401;

    public static String[] queue = new String[MAXN];

    public static int l, r;

    // 用于剪枝 pruning
    public static ArrayList<ArrayList<String>> graph = new ArrayList<>();

    static {
        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public static HashSet<String> visited = new HashSet<>();

    public int minStickers(String[] stickers, String target) {
        // 清空
        for (int i = 0; i < 26; i++) {
            graph.get(i).clear();
        }
        visited.clear();
        for (String str : stickers) {
            str = sort(str);
            // 加入包含该字母的贴纸 用于剪枝
            for (int i = 0; i < str.length(); i++) {
                if (i == 0 || str.charAt(i) != str.charAt(i - 1)) {
                    graph.get(str.charAt(i) - 'a').add(str);
                }
            }
        }
        target = sort(target);
        visited.add(target);
        l = r = 0;
        queue[r++] = target;
        int level = 0;
        // 使用队列的形式是整层弹出
        while (l < r) {
            int size = r - l;
            // 遍历这一层
            for (int i = 0; i < size; i++) {
                String cur = queue[l++];
                for (String s : graph.get(cur.charAt(0) - 'a')) {
                    String leftStr = next(cur, s);
                    if (leftStr.equals("")) {
                        return level + 1;
                    } else if (!visited.contains(leftStr)) {
                        visited.add(leftStr);
                        queue[r++] = leftStr;
                    }
                }
            }
            level++;
        }
        return -1;
    }

    public static String next(String t, String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 0; i < t.length(); ) {
            if (j == s.length()) {
                sb.append(t.charAt(i++));
            } else {
                if (t.charAt(i) < s.charAt(j)) {
                    sb.append(t.charAt(i++));
                } else if (t.charAt(i) > s.charAt(j)) {
                    j++;
                } else {
                    i++;
                    j++;
                }
            }
        }
        return sb.toString();
    }

    public static String sort(String str) {
        char[] s = str.toCharArray();
        Arrays.sort(s);
        return String.valueOf(s);
    }
}
