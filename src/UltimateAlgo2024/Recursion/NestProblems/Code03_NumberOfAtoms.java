package UltimateAlgo2024.Recursion.NestProblems;

import java.util.TreeMap;

// https://leetcode.com/problems/number-of-atoms/
//递归过程中有两种情况需要统计，
// 1. 前面为单个原子，如Mg2，直接在map中更新即可；
// 2.前面为一段嵌套的分子式，如(OH)2，需要先额外再建个表然后将内容统一取出来乘以总体的角标
public class Code03_NumberOfAtoms {

    public String countOfAtoms(String formula) {
        where = 0;
        TreeMap<String, Integer> map = f(formula.toCharArray(), where);
        StringBuilder ans = new StringBuilder();
        for (String key : map.keySet()) {
            ans.append(key);
            int cnt = map.get(key);
            if (cnt > 1) {
                ans.append(cnt);
            }
        }
        return ans.toString();
    }

    public static int where;

    public TreeMap<String, Integer> f(char[] s, int i) {
        TreeMap<String, Integer> ans = new TreeMap<>();
        // preInfo: 1.name of atom 2.a map
        StringBuilder name = new StringBuilder();
        TreeMap<String, Integer> pre = null;
        // how many times
        int cnt = 0;
        while (i < s.length && s[i] != ')') {
            if (s[i] >= 'A' && s[i] <= 'Z' || s[i] == '(') {
                fill(ans, name, pre, cnt);
                name.setLength(0);
                cnt = 0;
                pre = null;
                if (s[i] >= 'A' && s[i] <= 'Z') {
                    name.append(s[i++]);
                } else {
                    pre = f(s, i + 1);
                    i = where + 1;
                }
            } else if (s[i] >= 'a' && s[i] <= 'z') {
                name.append(s[i++]);
            } else {
                cnt = cnt * 10 + s[i++] - '0';
            }
        }
        fill(ans, name, pre, cnt);
        where = i;
        return ans;
    }

    public void fill(TreeMap<String, Integer> ans, StringBuilder name, TreeMap<String, Integer> pre, int cnt) {
        if (!name.isEmpty() || pre != null) {
            cnt = cnt == 0 ? 1 : cnt;
            if (!name.isEmpty()) {
                String key = name.toString();
                ans.put(key, ans.getOrDefault(key, 0) + cnt);
            } else {
                for (String key : pre.keySet()) {
                    ans.put(key, ans.getOrDefault(key, 0) + pre.get(key) * cnt);
                }
            }
        }
    }
}
