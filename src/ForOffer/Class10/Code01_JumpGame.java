package ForOffer.Class10;

// 本题测试链接 : https://leetcode.com/problems/jump-game-ii/
public class Code01_JumpGame {

    /**
     * Input: nums = [2,3,1,1,4]
     * Output: 2
     * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
     * @param arr
     * @return
     */
    public int jump(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // step步数之内，最远能到cur
        // next：如果能多跳一步，最远能到哪
        int step = 0;
        int cur = 0;
        int next = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (cur < i) {
                step++;
                cur = next;
            }
            next = Math.max(next, i + arr[i]);
        }
        return step;
    }
}
