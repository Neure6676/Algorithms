package UltimateAlgo2024.BitwiseOperations;

// https://leetcode.com/problems/power-of-two/description/
public class Code06_PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && n == (n & -n);
    }
}
