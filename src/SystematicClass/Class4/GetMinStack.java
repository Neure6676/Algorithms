package SystematicClass.Class4;

import java.util.Stack;

/**
 * 实现有getMin功能的栈
 *
 * 另外存一个只放最小数字的同步增长的栈
 */
public class GetMinStack {

    public static class MyStack {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int value) {
            stackData.push(value);
            if (stackMin.isEmpty()) {
                stackMin.push(value);
            } else if (value < this.getMin()) {
                    stackMin.push(value);
            } else {
                    stackMin.push(stackMin.peek());
            }
        }

        public int pop() {
            if (stackData.isEmpty()) {
                throw new IllegalStateException();
            }
            stackMin.pop();
            return stackData.pop();
        }

        private int getMin() {
            if (stackData.isEmpty()) {
                throw new IllegalStateException();
            }
            return stackMin.peek();
        }

    }
}
