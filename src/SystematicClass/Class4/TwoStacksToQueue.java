package SystematicClass.Class4;

import java.util.Stack;

/**
 * 用两个栈实现队列
 *
 * 延伸：
 * 图的宽度优先遍历（通常是用队列实现）用栈实现
 * 图的深度优先遍历（通常是用栈实现）用队列实现
 *
 * 注意点：倒数据时：
 * 1。push栈要一次倒完
 * 2。pop拿空之后才能继续倒数据
 *
 */
public class TwoStacksToQueue {

    public static class TwoStacksQueue {
        public Stack<Integer> stackPush;
        public Stack<Integer> stackPop;

        public TwoStacksQueue() {
            stackPush = new Stack<Integer>();
            stackPop = new Stack<Integer>();
        }

        //从push向pop倒数据
        private void pushToPop() {
            if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }

        public void add(int value) {
            stackPush.push(value);
        }

        public int poll() {
            if (stackPush.isEmpty() && stackPop.isEmpty()) {
                throw new IllegalStateException();
            }
            pushToPop();
            return stackPop.pop();
        }

        public int peek() {
            if (stackPush.isEmpty() && stackPop.isEmpty()) {
                throw new IllegalStateException();
            }
            pushToPop();
            return stackPop.peek();
        }
    }

    public static void main(String[] args) {
        TwoStacksQueue test = new TwoStacksQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }
}
