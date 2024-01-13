package UltimateAlgo2024.BitwiseOperations;

// https://www.nowcoder.com/practice/d2707eaf98124f1e8f1d9c18ad487f76
public class Code01_GetMaxWithoutJudge {

    // 必须保证n一定是0或者1
    // 0变1，1变0
    public static int flip(int n) {
        return n ^ 1;
    }

    // 非负数返回1
    // 负数返回0
    public static int sign(int n) {
        return flip(n >>> 31);
    }

    public static int getMax(int a, int b) {
        // c可能是溢出的
        int c = a - b;
        // a的符号
        int sa = sign(a);
        // b的符号
        int sb = sign(b);
        // c的符号
        int sc = sign(c);
        // 判断A和B，符号是不是不一样，如果不一样diffAB=1，如果一样diffAB=0
        int diffAB = sa ^ sb;
        // 判断A和B，符号是不是一样，如果一样sameAB=1，如果不一样sameAB=0
        int sameAB = flip(diffAB);
        int returnA = diffAB * sa + sameAB * sc;
        int returnB = flip(returnA);
        return a * returnA + b * returnB;
    }
}
