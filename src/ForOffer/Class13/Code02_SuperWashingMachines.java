package ForOffer.Class13;

// 本题测试链接 : https://leetcode.com/problems/super-washing-machines/
public class Code02_SuperWashingMachines {

    // 假设i位置左侧一共欠a件 右侧富余b件 那么i位置需要max(a, b)轮才能结束
    // 假设i位置左侧一共富余a件 右侧富余b件 那么i位置需要max(a, b)轮才能结束
    // 假设i位置左侧一共欠a件 右侧欠b件 那么i位置需要a + b轮才能结束

    // 思想：先求单点的答案
    public static int findMinMoves(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int size = arr.length;
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += arr[i];
        }
        if (sum % size != 0) { // 必须能均分
            return -1;
        }
        int avg = sum / size;
        int leftSum = 0;
        int ans = 0;
        for (int i = 0; i < size; i++) {
            int leftRest = leftSum - i * avg; // 左侧欠几件或富余几件情况
            int rightRest = (sum - leftSum - arr[i]) - (size - i - 1) * avg;
            if (leftRest < 0 && rightRest < 0) {
                ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
            } else {
                ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
            }
            leftSum += arr[i];
        }
        return ans;
    }
}
