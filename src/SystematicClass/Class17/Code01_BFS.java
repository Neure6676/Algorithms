package SystematicClass.Class17;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Code01_BFS {
    //相比树的BFS加一个set结构，每进栈一个点，在set中注册，防止重复
    public static void BFS(Node start) { // 指定一个出发点
        if (start == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>(); // 用LinkedList实现
        HashSet<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {  // 每次出栈，查看它所有的孩子
                if (set.contains(next.value)) {
                    continue;
                }
                queue.add(next);
                set.add(next);
            }
        }
    }

}
