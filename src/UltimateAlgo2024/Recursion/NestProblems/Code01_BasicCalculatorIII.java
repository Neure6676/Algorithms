package UltimateAlgo2024.Recursion.NestProblems;

import java.util.ArrayList;

// https://leetcode.com/problems/basic-calculator-iii/
public class Code01_BasicCalculatorIII {

    public static int where;

    public int calculate(String s) {
        where = 0;
        return f(s.toCharArray(), where);
    }

    // s[i....]，stop if i = s.length or ')'
    // return the result and update idx
    public int f(char[] str, int i) {
        int cur = 0;
        ArrayList<Integer> nums = new ArrayList<>();
        ArrayList<Character> ops = new ArrayList<>();
        while (i < str.length && str[i] != ')') {
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + str[i++] - '0';
            } else if (str[i] != '(') {
                push(nums, ops, cur, str[i++]);
                cur = 0;
            } else {
                cur = f(str, i + 1);
                i = where + 1;
            }
        }
        push(nums, ops, cur, '+');
        where = i;
        return compute(nums, ops);
    }

    public static void push(ArrayList<Integer> numbers, ArrayList<Character> ops, int cur, char op) {
        int n = numbers.size();
        if (n == 0 || ops.get(n - 1) == '+' || ops.get(n - 1) == '-') {
            numbers.add(cur);
            ops.add(op);
        } else {
            int topNumber = numbers.get(n - 1);
            char topOp = ops.get(n - 1);
            if (topOp == '*') {
                numbers.set(n - 1, topNumber * cur);
            } else {
                numbers.set(n - 1, topNumber / cur);
            }
            ops.set(n - 1, op);
        }
    }

    public static int compute(ArrayList<Integer> numbers, ArrayList<Character> ops) {
        int n = numbers.size();
        int ans = numbers.get(0);
        for (int i = 1; i < n; i++) {
            ans += ops.get(i - 1) == '+' ? numbers.get(i) : -numbers.get(i);
        }
        return ans;
    }

}
