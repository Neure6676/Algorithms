package ForOffer.Class17;

// 本题测试链接 : https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
public class Code02_KthSmallestElementInSortedMatrix {

    /**
     * 假设min:1 max:1000
     * 小于等于（min + max）/2 （也就是500）的有几个，最接近它的是谁
     * 假设二分到785时 小于等于它的数正好是100，但数组中可能没有785，取最接近的
     * O((m+n)*log(max-min))
     */
    public int kthSmallest(int[][] matrix, int k) {
        int N = matrix.length;
        int M = matrix[0].length;
        int right = matrix[N - 1][M - 1];
        int left = matrix[0][0];
        int ans = 0;
        // 二分
        while (right >= left) {
            int mid = (right + left) / 2;
            Info info = findLessNum(matrix, mid);
            if (info.lessNum < k) {
                left = mid + 1;
            } else {
                ans = info.closest;
                right = mid -1;
            }
        }
        return ans;
    }

    public static class Info {
        public int lessNum;
        public int closest;

        public Info(int l, int c) {
            lessNum = l;
            closest = c;
        }

    }
    public static Info findLessNum(int[][] matrix, int num) {
        int row = 0;
        int col = matrix[0].length - 1;
        int lessNum = 0;
        int closest = Integer.MIN_VALUE;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] <= num) {
                lessNum += col + 1;
                closest = Math.max(closest, matrix[row][col]);
                row++;
            } else {
                col--;
            }
        }
        return new Info(lessNum, closest);
    }

}
