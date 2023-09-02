package ForOffer.Class17;

/**
 * 给定一个每一行有序、每一列也有序，整体可能无序的二维数组，再给定一个数num，返回二维数组中有没有num这个数
 */
public class Code01_FindNumInSortedMatrix {

    // 如果x大于100 下面的数不可能
    // 如果x小于100 左边的数不可能
    // 从右往左 从上往下
    // O(n + m)
    public static boolean isContains(int[][] matrix, int k) {
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == k) {
                return true;
            } else if (matrix[row][col] > k){
                col--;
            } else {
                row++;
            }
        }
        return false;
    }
}
