package UltimateAlgo2024.Graph.Part5DBFS;

import java.util.HashSet;
import java.util.List;

// https://leetcode.com/problems/word-ladder/
public class Code01_WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 总词表
        HashSet<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }
        HashSet<String> smallLevel = new HashSet<>();
        HashSet<String> largeLevel = new HashSet<>();
        HashSet<String> nextLevel = new HashSet<>();
        smallLevel.add(beginWord);
        largeLevel.add(endWord);
        for (int level = 2; !smallLevel.isEmpty(); level++) {
            // 从小的那侧开始扩
            for (String w : smallLevel) {
                char[] word = w.toCharArray();
                // 每一位字符都试
                for (int j = 0; j < word.length; j++) {
                    char old = word[j];
                    // 每一位字符都从a到z换一遍
                    for (char change = 'a'; change <= 'z'; change++) {
                        if (change != old) {
                            word[j] = change;
                            String next = String.valueOf(word);
                            if (largeLevel.contains(next)) {
                                return level;
                            }
                            if (dict.contains(next)) {
                                // 词表中有才有效
                                // 删去避免重复
                                dict.remove(next);
                                nextLevel.add(next);
                            }
                        }
                    }
                    // 恢复现场
                    word[j] = old;
                }
            }
            if (nextLevel.size() <= largeLevel.size()) {
                HashSet<String> temp = smallLevel;
                smallLevel = nextLevel;
                nextLevel = temp;
            } else {
                HashSet<String> temp = smallLevel;
                smallLevel = largeLevel;
                largeLevel = nextLevel;
                nextLevel = temp;
            }
            nextLevel.clear();
        }
        return 0;
    }
}
