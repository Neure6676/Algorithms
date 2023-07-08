package SystematicClass.Class21;

/**
 * 请同学们自行搜索或者想象一个象棋的棋盘，
 * 然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
 * 那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
 * 给你三个 参数 x，y，k
 * 返回“马”从(0,0)位置出发，必须走k步
 * 最后落在(x,y)上的方法数有多少种?
 */
public class Code02_HorseJump {

    // 当前来到的位置是（x,y）可变
    // 还剩下rest步需要跳 可变      三维dp
    // 跳完rest步，正好跳到a，b的方法数是多少？
    // 10 * 9
    public static int jump1(int x, int y, int k) {
        return process(0, 0, k, x, y);
    }

    public static int process(int x, int y, int rest, int a, int b) {
        // 越界
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        // base case
        if (rest == 0) {
            return (x == a && y == b) ? 1 : 0;
        }
        int ways = process(x + 2, y + 1, rest - 1, a, b);
        ways += process(x + 1, y + 2, rest - 1, a, b);
        ways += process(x - 1, y + 2, rest - 1, a, b);
        ways += process(x - 2, y + 1, rest - 1, a, b);
        ways += process(x - 2, y - 1, rest - 1, a, b);
        ways += process(x - 1, y - 2, rest - 1, a, b);
        ways += process(x + 1, y - 2, rest - 1, a, b);
        ways += process(x + 2, y - 1, rest - 1, a, b);
        return ways;
    }





    // dp O(10*9*K)
    public static int jump2(int a, int b, int k) {
        int[][][] dp = new int[10][9][k+1];  // 存所有递归过程
        // 因为递归中调的子过程参数都是rest-1，所以只依赖下一层，同层不互相依赖
        // 看base case，rest为0时，只有（a，b）位置是1，其他全是0
        dp[a][b][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 9; y++) {
                    int ways = pick(dp, x + 2, y + 1, rest - 1);
                    ways += pick(dp, x + 1, y + 2, rest - 1);
                    ways += pick(dp, x - 1, y + 2, rest - 1);
                    ways += pick(dp, x - 2, y + 1, rest - 1);
                    ways += pick(dp, x - 2, y - 1, rest - 1);
                    ways += pick(dp, x - 1, y - 2, rest - 1);
                    ways += pick(dp, x + 1, y - 2, rest - 1);
                    ways += pick(dp, x + 2, y - 1, rest - 1);
                    dp[x][y][rest] = ways;
                }
            }
        }
        return dp[0][0][k]; //看主函数怎么调
    }


    // 防止越界
    public static int pick(int[][][] dp, int x, int y, int rest) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        return dp[x][y][rest];
    }



    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(jump1(x, y, step));
        System.out.println(jump2(x, y, step));
    }
}
