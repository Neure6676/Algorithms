package UltimateAlgo2024.StackAndQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code01_QueueStackAndCircularQueue {

    // implement with doubly linked list
    public static class Queue1 {
        Queue<Integer> queue = new LinkedList<>();

        public boolean isEmpty() {
            return queue.isEmpty();
        }

        public void offer(int val) {
            queue.offer(val);
        }

        public int poll() {
            return queue.poll();
        }

        public int peek() {
            return queue.peek();
        }

        public int size() {
            return queue.size();
        }
    }

    // implement with array
    public static class Queue2 {
        public int[] queue;
        public int l;
        public int r;

        public Queue2(int n) {
            queue = new int[n];
            l = 0;
            r = 0;
        }

        public boolean isEmpty() {
            return l == r;
        }

        public void offer(int val) {
            queue[r++] = val;
        }

        public int poll() {
            return queue[l++];
        }

        public int peek() {
            return queue[l];
        }

        public int tail() {
            return queue[r - 1];
        }

        public int size() {
            return r - l;
        }
    }


    public static class Stack1 {
        Stack<Integer> stack = new Stack<>();

        public boolean isEmpty() {
            return stack.isEmpty();
        }

        public void push(int val) {
            stack.push(val);
        }

        public int peek() {
            return stack.peek();
        }

        public int pop() {
            return stack.pop();
        }

        public int size() {
            return stack.size();
        }
    }

    // implement with array
    public static class Stack2 {
        public int[] stack;
        public int size;

        public Stack2(int n) {
            stack = new int[n];
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void push(int val) {
            stack[size++] = val;
        }

        public int peek() {
            return stack[size - 1];
        }

        public int pop() {
            return stack[--size];
        }

        public int size() {
            return size;
        }
    }

    // https://leetcode.com/problems/design-circular-queue/description/
    class MyCircularQueue {

        public int[] queue;
        public int l;
        public int r;
        public int size;

        public MyCircularQueue(int k) {
            queue = new int[k];
            l = 0;
            r = 0;
            size = 0;
        }

        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            queue[r] = value;
            r = r + 1 == queue.length ? 0 : r + 1;
            size++;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            l = l + 1 == queue.length ? 0 : l + 1;
            size--;
            return true;
        }

        public int Front() {
            if (isEmpty()) {
                return -1;
            }
            return queue[l];
        }

        public int Rear() {
            if (isEmpty()) {
                return -1;
            }
            int index = r == 0 ? queue.length - 1 : r - 1;
            return queue[index];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == queue.length;
        }
    }

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
}
