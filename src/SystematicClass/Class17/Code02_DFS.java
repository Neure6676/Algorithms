package SystematicClass.Class17;

import java.util.HashSet;
import java.util.Stack;

// 一条路走到头，依次回到上一个，看还有没有其他路
public class Code02_DFS {
    // 递归
    // 也需要一个set把所有遍历过的记下来
    public static void DFS(Node start) {
        if (start == null) {
            return;
        }
        HashSet<Node> set = new HashSet<>();
        set.add(start);
        process(start, set);
    }

    private static void process(Node node, HashSet<Node> set) {
        if (node == null) {
            return;
        }
        System.out.println(node.value);
        set.add(node);
        for (Node next : node.nexts) {
            if (!set.contains(next)) {
                process(next, set);
            }
        }
    }

//    // 不使用递归方法，自己压栈
//    public static void DFS(Node start) {
//        if (start == null) {
//            return;
//        }
//        Stack<Node> stack = new Stack<>();
//        HashSet<Node> set = new HashSet<>();
//        stack.add(start);
//        set.add(start);
//        System.out.println(start.value);  // 入栈就打印。这个时机就是DFS的时机，可换成其他操作
//        while (!stack.isEmpty()) {
//            Node cur = stack.pop();
//            for (Node next : cur.nexts) {
//                if (!set.contains(next)) { // 如果发现其中一个支路没被登记过，则将他自己和那条支路压栈
//                    stack.push(cur); // 先压自己进栈
//                    stack.push(next);
//                    set.add(next);
//                    System.out.println(next.value);
//                    break;   // 跳出了for循环，去找这一整条路，栈中其实是目前整条路径
//                }
//            }
//        }
//    }



}
