package UltimateAlgo2024.BitwiseOperations;

// 返回大于等于n的最小的2某次方
// 如果int范围内不存在这样的数，返回整数最小值
public class Code08_Near2power {

    public static final int near2power(int n) {
        if (n <= 0) {
            return 1;
        }
        n--; // 防止它自己就是2的某次方
        // 把最左侧1后的所有位变成1，最后加1即可
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }

    public static void main(String[] args) {
        int number = 100;
        System.out.println(near2power(number));
    }

}
