package ForOffer.Class9;

import java.util.HashMap;

// 定义何为step sum？比如680，680 + 68 + 6 = 754，680的step sum叫754。给定一个正数num，判断它是不是某个数的step sum
public class Code05_IsStepSum {

    public static boolean isStepSum(int num) {
        int L = 0;
        int R = num;
        int mid = 0;
        while (L <= R) {
            mid = (L + R) / 2;
            int midStepSum = calculateStepSum(mid);
            if (midStepSum == num) {
                return true;
            } else if (midStepSum < num) {
                L = midStepSum + 1;
            } else {
                R = midStepSum - 1;
            }
        }
        return false;
    }

    private static int calculateStepSum(int num) {
        int ans = 0;
        while (num != 0) {
            ans += num;
            num /= 10;
        }
        return ans;
    }




    // for test
    public static HashMap<Integer, Integer> generateStepSumNumberMap(int numMax) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= numMax; i++) {
            map.put(calculateStepSum(i), i);
        }
        return map;
    }

    // for test
    public static void main(String[] args) {
        int max = 100;
        int maxStepSum = calculateStepSum(max);
        HashMap<Integer, Integer> ans = generateStepSumNumberMap(max);
        System.out.println("测试开始");
        for (int i = 0; i <= maxStepSum; i++) {
            if (isStepSum(i) ^ ans.containsKey(i)) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }
}
