package ForOffer.Class17;

import java.util.List;

/**
 * 给定一个字符串数组arr，里面都是互不相同的单词，找出所有不同的索引对(i, j)，使得列表中的两个单词，words[i] + words[j]，可拼接成回文串。
 * Leetcode题目：https://leetcode.com/problems/palindrome-pairs/
 */
public class Code03_PalindromePairs1 {

    /**
     * 思路：从每个String出发，看他需要什么，而不是拼起来再验证
     * 举例：aab
     * 前：1。a + ab 需要ba拼前面
     *    2。aa + b 需要b拼前面
     * 后：1。aa + b 需要aa拼后面
     * 前缀是回文的试一遍，后缀是回文的试一遍 去set里面找
     *
     * 注意：此处每次查hashset的操作是O(k) k是字符串平均长度 此时长度不能忽略 必须遍历一遍算hash值
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        return null;
    }
}
