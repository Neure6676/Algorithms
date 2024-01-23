package UltimateAlgo2024.Tree.Trie;

import java.util.ArrayList;
import java.util.Arrays;

// https://leetcode.com/problems/implement-trie-ii-prefix-tree/
public class Code01_TrieTree {

    class Trie {

        public static int MAXN = 2001;

        public static int[][] trie = new int[MAXN][26];
        public static int[] end = new int[MAXN];
        public static int[] pass = new int[MAXN];
        public static int cnt;


        public Trie() {
            clear();
            cnt = 1;
        }

        public void insert(String word) {
            int cur = 1;
            pass[cur]++;
            for (int i = 0, path; i < word.length(); i++) {
                path = word.charAt(i) - 'a';
                if (trie[cur][path] == 0) {
                    trie[cur][path] = ++cnt;
                }
                cur = trie[cur][path];
                pass[cur]++;
            }
            end[cur]++;
        }

        public int countWordsEqualTo(String word) {
            int cur = 1;
            for (int i = 0, path; i < word.length(); i++) {
                path = word.charAt(i) - 'a';
                if (trie[cur][path] == 0) {
                    return 0;
                }
                cur = trie[cur][path];
            }
            return end[cur];
        }

        public int countWordsStartingWith(String prefix) {
            int cur = 1;
            for (int i = 0, path; i < prefix.length(); i++) {
                path = prefix.charAt(i) - 'a';
                if (trie[cur][path] == 0) {
                    return 0;
                }
                cur = trie[cur][path];
            }
            return pass[cur];
        }

        public void erase(String word) {
            if (countWordsEqualTo(word) > 0) {
                int cur = 1;
                pass[cur]--;
                for (int i = 0, path; i < word.length(); i++) {
                    path = word.charAt(i) - 'a';
                    if (--pass[trie[cur][path]] == 0) {
                        trie[cur][path] = 0;
                        return;
                    }
                    cur = trie[cur][path];
                }
                end[cur]--;
            }
        }

        public static void clear() {
            for (int i = 1; i <= cnt; i++) {
                Arrays.fill(trie[i], 0);
                end[i] = 0;
                pass[i] = 0;
            }
        }
    }

    class Trie1 {

        class TrieNode {
            public int pass;
            public int end;
            public TrieNode[] nexts;

            public TrieNode() {
                pass = 0;
                end = 0;
                nexts = new TrieNode[26];
            }
        }

        private TrieNode root;

        public Trie1() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = root;
            node.pass++;
            for (int i = 0, path; i < word.length(); i++) { // 从左往右遍历字符
                path = word.charAt(i) - 'a'; // 由字符，对应成走向哪条路
                if (node.nexts[path] == null) {
                    node.nexts[path] = new TrieNode();
                }
                node = node.nexts[path];
                node.pass++;
            }
            node.end++;
        }

        // 如果之前word插入过前缀树，那么此时删掉一次
        // 如果之前word没有插入过前缀树，那么什么也不做
        public void erase(String word) {
            if (countWordsEqualTo(word) > 0) {
                TrieNode node = root;
                node.pass--;
                for (int i = 0, path; i < word.length(); i++) {
                    path = word.charAt(i) - 'a';
                    if (--node.nexts[path].pass == 0) {
                        node.nexts[path] = null;
                        return;
                    }
                    node = node.nexts[path];
                }
                node.end--;
            }
        }

        // 查询前缀树里，word单词出现了几次
        public int countWordsEqualTo(String word) {
            TrieNode node = root;
            for (int i = 0, path; i < word.length(); i++) {
                path = word.charAt(i) - 'a';
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.end;
        }

        // 查询前缀树里，有多少单词以pre做前缀
        public int countWordsStartingWith(String pre) {
            TrieNode node = root;
            for (int i = 0, path; i < pre.length(); i++) {
                path = pre.charAt(i) - 'a';
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.pass;
        }

    }
}
