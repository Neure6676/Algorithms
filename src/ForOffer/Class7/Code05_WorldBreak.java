package ForOffer.Class7;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 假设所有字符都是小写字母，大字符串是str，arr是去重的单词表,
 * 每个单词都不是空字符串且可以使用任意次。
 * 使用arr中的单词有多少种拼接str的方式，返回方法数。
 */
public class Code05_WorldBreak {


    // 从左往右模型
    // dp[i]表示从i往后有多少种分法
    // O(N ^ 3) !
    public static int ways1(String str, String[] arr) {
        HashSet<String> set = new HashSet<>(Arrays.asList(arr));
        return process(str, 0, set);
    }

    public static int process(String str, int i, HashSet<String> set) {
        if (i == str.length()) { // 没字符串需要分解了
            return 1;
        }
        int ways = 0;
        for (int end = i; end < str.length(); end++) {
            String pre =  str.substring(i, end + 1); //[)
            if (set.contains(pre)) {
                ways += process(str, end + 1, set);
            }
        }
        return ways;
    }

    // 用Trie优化
    public static class Node {
        public boolean end;
        public Node[] nexts;

        public Node() {
            end = false;
            nexts = new Node[26];
        }
    }

    public static int ways2(String str, String[] arr) {
        Node root = new Node();
        // 将arr中每一个String挂到Trie上
        for (String cur : arr) {
            char[] chs = cur.toCharArray();
            Node node = root;
            int index = 0;
            for (char ch : chs) {
                index = ch -'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node();
                }
                node = node.nexts[index];
            }
            node.end = true;
        }
        return g(str.toCharArray(), root, 0);
    }

    // str[i...] 被分解的方法数，返回

    public static int g(char[] str, Node root, int i) {
        if (i == str.length) {
            return 1;
        }
        int ways = 0;
        Node cur = root;
        // i...end
        for (int end = i; end < str.length; end++) {
            int path = str[end] - 'a';
            if (cur.nexts[path] == null) {
                break;
            }
            cur = cur.nexts[path];
            if (cur.end) { // i...end
                ways += g(str, root, end + 1);
            }
        }
        return ways;
    }
}
