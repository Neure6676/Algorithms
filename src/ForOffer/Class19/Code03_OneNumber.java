package ForOffer.Class19;

/**
 * 给定一个正数N，比如N = 13，在纸上把所有数都列出来如下：
 * 1 2 3 4 5 6 7 8 9 10 11 12 13
 * 可以数出1这个字符出现了6次，给定一个正数N，如果把1~N都列出来，返回1这个字符出现的多少次
 * https://leetcode.com/problems/number-of-digit-one/
 */
public class Code03_OneNumber {

    /**
     * 数位dp
     * n = 1364
     * 365~1364 + 1~364
     * 算365~1364有几个1 剩下的继续递归
     * 千位：1000～1364能在千位上提供365个1
     * 百位：365～999不能 1000～1364相当于0～364 其中100～199 提供100个1
     *      结论：十位个位上随意变 他俩确定后千位自动确定 所以是10*10=100
     * 十位：结论：百位个位上随意变 他俩确定后十位自动确定 所以是10*10=100
     * 个位：结论：百位十位上随意变 他俩确定后个位自动确定 所以是10*10=100
     *
     * 公式(最高位1)：若1364中364位rest 一共k位
     * rest+1 + (k-1)*10^(k-2)
     *
     * 公式(最高位是x)
     * 最高位：10^(k-1)个1
     * 剩下的k-1位 每位x*(10^(k-2))个1
     *
     */
    public int countDigitOne(int n) {
        if (n < 1) {
            return 0;
        }
        // n有几位
        int len = getLenOfN(n);
        if (len == 1) {
            return 1;
        }
        // num 13625
        // tmp1 10000
        //
        // num 7872328738273
        // tmp1 1000000000000  (10的len-1次方)
        int tmp1 = (int) Math.pow(10, len - 1);
        // num最高位
        int highest = n / tmp1;
        // 最高位有多少1
        int highestOneNum = highest == 1 ? n % tmp1 + 1 : tmp1;
        int restOneNum = (len - 1) * (tmp1 / 10) * highest;
        return highestOneNum + restOneNum + countDigitOne(n % tmp1); //剩下的递归
    }

    public static int getLenOfN(int n) {
        int len = 0;
        while (n != 0) {
            n /= 10;
            len++;
        }
        return len;
    }

//    public static void main(String[] args) {
//        System.out.println(getLenOfN(11));
//    }

}
