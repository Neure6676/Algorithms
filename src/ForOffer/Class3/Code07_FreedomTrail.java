package ForOffer.Class3;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 电子游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门
 * 给定一个字符串ring，表示刻在外环上的编码；给定另一个字符串key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数
 * 最初，ring的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使key的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完key中的所有字符
 * 旋转ring拼出 key 字符key[i]的阶段中：
 * 您可以将ring顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串ring的一个字符与 12:00 方向对齐，并且这个字符必须等于字符key[i] 。
 * 如果字符key[i]已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作1 步。按完之后，您可以开始拼写key的下一个字符（下一阶段）, 直至完成所有拼写。
 * Leetcode题目：https://leetcode.com/problems/freedom-trail/
 */
public class Code07_FreedomTrail {

    // 电话里指针指着的上一个按键preButton
    // 此时要搞定哪个字符？index
    // map : key 一种字符
    //       value 哪些位置拥有这个字符
    // N: 电话大小 dial函数需要
    // f(0, 0, str, map, N)
    public static int process(int preButton, int index, char[] str, HashMap<Character, ArrayList<Integer>> map, int N, int[][] dp) {
        //缓存命中
        if (dp[preButton][index] != -1) {
            return dp[preButton][index];
        }
        int ans = Integer.MAX_VALUE;
        if (index == str.length) {
            ans = 0;
        } else {
            // 还有字符需要搞定
            char cur = str[index];
            ArrayList<Integer> nextPositions = map.get(cur);
            for (int next : nextPositions) {
                int cost = dial(preButton, next, N) + 1 + process(next, index + 1, str, map, N, dp);
                ans = Math.min(ans, cost);
            }
        }
        dp[preButton][index] = ans;
        return ans;
    }

    public static int findRotateSteps(String r, String k) {
        char[] ring = r.toCharArray();
        int N = ring.length;
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (!map.containsKey(ring[i])) {
                map.put(ring[i], new ArrayList<>());
            }
            map.get(ring[i]).add(i);
        }
        char[] str = k.toCharArray();
        int M = str.length;
        int[][] dp = new int[N][M + 1];
        // dp[][] == -1 表示没算过
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= M; j++) {
                dp[i][j] = -1;
            }
        }
        return process(0, 0, str, map, N, dp);
    }

    // 返回i1到i2最短距离
    public static int dial(int i1, int i2, int size) {
        return Math.min(Math.abs(i1 - i2), size - (Math.max(i1, i2) - Math.min(i1, i2)));
    }

}
