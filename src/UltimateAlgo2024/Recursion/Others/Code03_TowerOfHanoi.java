package UltimateAlgo2024.Recursion.Others;

// 打印n层汉诺塔问题的最优移动轨迹
public class Code03_TowerOfHanoi {

    public static void hanoi(int n) {
        if (n > 0) {
            f(n, "left", "right", "mid");
        }
    }

    public static void f(int i, String from, String to, String other) {
        if (i == 1) {
            System.out.println("Move 1 from " + from + " to " + to);
        } else {
            f(i - 1, from, other, to);
            System.out.println("Move " + i + " from " + from + " to " + to);
            f(i - 1, other, to, from);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        hanoi(n);
    }
}
