package SystematicClass.Class18;

// 打印n层汉诺塔从最左边移动到最右边的全部过程（递归+非递归实现）
// 小压大
public class Code02_Hanoi {
    /**
     * 假设3个圆盘
     * 第一大步：1，2 左到中
     * 第二大步：3 左到右
     * 第三大步：1，2 中到右
     */

    public static void hanoi1(int n) {
        leftToRight(n);
    }

    // 共六种操作
    public static void leftToRight(int n) {
        if (n == 1) { // base case
            System.out.println("Move 1 from left to right.");
        }
        leftToMid(n - 1);
        System.out.println("Move " + n + "from left to right");
        midToRight(n - 1);
    }

    private static void leftToMid(int n) {
        if (n == 1) {
            System.out.println("Move 1 from left to mid");
        }
        leftToRight(n - 1);
        System.out.println("Move " + n + "from left to mid");
        rightToMid(n - 1);
    }

    public static void rightToMid(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to mid");
            return;
        }
        rightToLeft(n - 1);
        System.out.println("Move " + n + " from right to mid");
        leftToMid(n - 1);
    }

    public static void midToRight(int n) {
        if (n == 1) {
            System.out.println("Move 1 from mid to right");
            return;
        }
        midToLeft(n - 1);
        System.out.println("Move " + n + " from mid to right");
        leftToRight(n - 1);
    }

    public static void midToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from mid to left");
            return;
        }
        midToRight(n - 1);
        System.out.println("Move " + n + " from mid to left");
        rightToLeft(n - 1);
    }

    public static void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to left");
            return;
        }
        rightToMid(n - 1);
        System.out.println("Move " + n + " from right to left");
        midToLeft(n - 1);
    }



    //简化：一个递归函数可以用增加参数的方法增加它的可能性
    public static void hanoi2(int n) {
        if (n > 0) {
            func(n, "left", "right", "mid");
        }
    }
    /**
     *     在：from
     *     去：to
     *     另一个： other
     */
    public static void func(int N, String from, String to, String other) {
        if (N == 1) { // base
            System.out.println("Move 1 from " + from + " to " + to);
        } else {
            func(N - 1, from, other, to);   // n-1个圆盘从from去other
            System.out.println("Move " + N + " from " + from + " to " + to);
            func(N - 1, other, to, from);   // n-1个圆盘从other去to
        }
    }
}
