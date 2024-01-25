package UltimateAlgo2024.Difference.TwoD;

// https://leetcode.com/problems/range-sum-query-2d-immutable/
public class Code01_PrefixSumMatrix {

    class NumMatrix {

        public static int[][] preSum;

        public NumMatrix(int[][] matrix) {
            int row = matrix.length;
            int col = matrix[0].length;
            // first row and first col are all 0's
            preSum = new int[row + 1][col + 1];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    // left + top - topleft + self
                    preSum[i + 1][j + 1] = preSum[i + 1][j] + preSum[i][j + 1] - preSum[i][j] + matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return preSum[row2 + 1][col2 + 1] - preSum[row1][col2 + 1] - preSum[row2 + 1][col1] + preSum[row1][col1];
        }
    }

}
