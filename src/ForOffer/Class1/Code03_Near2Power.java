package ForOffer.Class1;

// 给定一个非负整数num，如何不用循环语句，返回>=num，并且离num最近的，2的某次方
// 3 -> 4
// 7 -> 8
public class Code03_Near2Power {

    // n>>>1 将n右移一位
    // >> 带符号右移  >>> 不带符号右移
    // "｜" 或运算

    // 从最高位的1开始 让后面全是1
    // 已知n是正数
    // 返回大于等于，且最接近n的，2的某次方的值
    public static final int tableSizeFor(int n) {
        n--;  // 防止它本身就是2的某次方
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }

    public static void main(String[] args) {
        int cap = 120;
        System.out.println(tableSizeFor(cap));
    }

}
