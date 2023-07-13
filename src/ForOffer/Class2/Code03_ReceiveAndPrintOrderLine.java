package ForOffer.Class2;

import java.util.HashMap;

/**
 * 已知一个消息流会不断地吐出整数1~N，但不一定按照顺序依次吐出，如果上次打印的序号为i， 那么当i+1出现时
 * 请打印i+1及其之后接收过的并且连续的所有数，直到1~N全部接收并打印完，请设计这种接收并打印的结构
 */
public class Code03_ReceiveAndPrintOrderLine {

    // 头表 存每个连续区间的头
    // 尾表 存每个连续区间的尾
    // 单链表结构 每个Node包含1。信息 2。Next指针
    // 找以谁结尾的存不存在 去尾表
    public static class Node {
        public String info;
        public Node next;

        public Node(String str) {
            info = str;
        }
    }

    public static class MessageBox {
        public HashMap<Integer, Node> headmap;
        public HashMap<Integer, Node> tailmap;
        public int waitPoint; // 当前等的信息序号

        public MessageBox() {
            headmap = new HashMap<Integer, Node>();
            tailmap = new HashMap<Integer, Node>();
            waitPoint = 1;
        }

        /**
         * receive msg
         * @param num 消息编号
         * @param info 消息内容
         */
        public void receive(int num, String info) {
            if (num < 1) {
                return;
            }
            Node cur = new Node(info);
            // num~num的连续区间 分别放到两个表
            headmap.put(num, cur);
            tailmap.put(num, cur);
            // 查询有没有某个连续区间以num - 1结尾
            if (tailmap.containsKey(num - 1)) {
                tailmap.get(num - 1).next = cur;
                tailmap.remove(num - 1);
                headmap.remove(num);
            }
            // 查询有没有某个连续区间以num + 1结尾
            if (headmap.containsKey(num + 1)) {
                cur.next = headmap.get(num + 1);
                headmap.remove(num + 1);
                tailmap.remove(num);
            }
            if (num == waitPoint) {
                print();
            }
        }

        public void print() {
            Node node = headmap.get(waitPoint);
            headmap.remove(waitPoint);
            while (node != null) {
                System.out.print(node.info + " ");
                node = node.next;
                waitPoint++;
            }
            tailmap.remove(waitPoint - 1);
            System.out.println();
        }
    }




    public static void main(String[] args) {
        // MessageBox only receive 1~N
        MessageBox box = new MessageBox();
        // 1....
        System.out.println("这是2来到的时候");
        box.receive(2,"B"); // - 2"
        System.out.println("这是1来到的时候");
        box.receive(1,"A"); // 1 2 -> print, trigger is 1

        box.receive(4,"D"); // - 4
        box.receive(5,"E"); // - 4 5
        box.receive(7,"G"); // - 4 5 - 7
        box.receive(8,"H"); // - 4 5 - 7 8
        box.receive(6,"F"); // - 4 5 6 7 8
        box.receive(3,"C"); // 3 4 5 6 7 8 -> print, trigger is 3

        box.receive(9,"I"); // 9 -> print, trigger is 9
        box.receive(10,"J"); // 10 -> print, trigger is 10

        box.receive(12,"L"); // - 12
        box.receive(13,"M"); // - 12 13
        box.receive(11,"K"); // 11 12 13 -> print, trigger is 11

    }
}
