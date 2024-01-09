package UltimateAlgo2024.StackAndQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code02_ConvertBetweenQueueAndStack {

    // https://leetcode.com/problems/implement-queue-using-stacks/
    class MyQueue {
        Stack<Integer> inStack;
        Stack<Integer> outStack;

        public MyQueue() {
            inStack = new Stack<>();
            outStack = new Stack<>();
        }

        public void push(int x) {
            inStack.push(x);
        }

        public int pop() {
            if (!outStack.isEmpty()) {
                return outStack.pop();
            }
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
            return outStack.pop();
        }

        public int peek() {
            if (!outStack.isEmpty()) {
                return outStack.peek();
            }
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
            return outStack.peek();
        }

        public boolean empty() {
            return inStack.isEmpty() && outStack.empty();
        }
    }



    // https://leetcode.com/problems/implement-stack-using-queues/
    class MyStack {
        int size;
        Queue<Integer> inQueue;
        Queue<Integer> outQueue;

        public MyStack() {
            size = 0;
            inQueue = new LinkedList<>();
            outQueue = new LinkedList<>();
        }

        public void push(int x) {
            inQueue.add(x);
            size++;
        }

        public int pop() {
            if (empty()) {
                return -1;
            }
            int count = size;
            while (count > 1) {
                outQueue.add(inQueue.poll());
                count--;
            }
            size--;
            int ans = inQueue.poll();
            while (!outQueue.isEmpty()) {
                inQueue.add(outQueue.poll());
            }
            return ans;
        }

        public int top() {
            if (empty()) {
                return -1;
            }
            int count = size;
            while (count > 1) {
                outQueue.add(inQueue.poll());
                count--;
            }
            int ans = inQueue.peek();
            while (!inQueue.isEmpty()) {
                outQueue.add(inQueue.poll());
            }
            while (!outQueue.isEmpty()) {
                inQueue.add(outQueue.poll());
            }
            return ans;
        }

        public boolean empty() {
            return size == 0;
        }
    }

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
}
