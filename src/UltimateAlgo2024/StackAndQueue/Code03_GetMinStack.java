package UltimateAlgo2024.StackAndQueue;

import java.util.HashMap;
import java.util.Stack;

// https://leetcode.com/problems/min-stack/
public class Code03_GetMinStack {

    class MinStack {

        Stack<Integer> data;
        Stack<Integer> min;

        public MinStack() {
            data = new Stack<>();
            min = new Stack<>();
        }

        public void push(int val) {
            data.push(val);
            if (min.size() == 0 || min.peek() >= val) {
                min.push(val);
            } else {
                min.push(min.peek());
            }
        }

        public void pop() {
            data.pop();
            min.pop();
        }

        public int top() {
            return data.peek();
        }

        public int getMin() {
            return min.peek();
        }
    }
}
