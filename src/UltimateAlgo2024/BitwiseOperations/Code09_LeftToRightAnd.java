package UltimateAlgo2024.BitwiseOperations;

// https://leetcode.com/problems/bitwise-and-of-numbers-range/
// 101 110 111 -> 100
public class Code09_LeftToRightAnd {

    public int rangeBitwiseAnd(int left, int right) {
        //如果left < right， 例如right=111，则必然存在110，那么末尾一定是1
        while (left < right) {
            right -= right & -right;  // 每次除去最右侧的1
        }
        return right;
    }

}
