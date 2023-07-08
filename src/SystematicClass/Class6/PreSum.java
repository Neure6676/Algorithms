package SystematicClass.Class6;

public class PreSum {
    public static class RangeSum {

        private int[] preSum;

        public RangeSum(int[] arr) {
            int N = arr.length;
            //前缀和数组
            preSum = new int[N];
            preSum[0] = arr[0];
            for (int i = 1; i < N; i++) {
                preSum[i] = preSum[i-1] + arr[i];
            }
        }

        public int rangeSum(int L, int R){
            return L == 0 ? preSum[R] : preSum[R] - preSum[L-1];
        }
    }
}
