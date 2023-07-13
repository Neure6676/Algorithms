package ForOffer.Class2;

public class Code02_Cola {

    /*
     * 买饮料 时间限制： 3000MS 内存限制： 589824KB 题目描述：
     * 游游今年就要毕业了，和同学们在携程上定制了日本毕业旅行。愉快的一天行程结束后大家回到了酒店房间，这时候同学们都很口渴，
     * 石头剪刀布选出游游去楼下的自动贩卖机给大家买可乐。 贩卖机只支持硬币支付，且收退都只支持10 ，50，100
     * 三种面额。一次购买行为只能出一瓶可乐，且每次购买后总是找零最小枚数的硬币。（例如投入100圆，可乐30圆，则找零50圆一枚，10圆两枚）
     * 游游需要购买的可乐数量是 m，其中手头拥有的 10,50,100 面额硬币的枚数分别是 a,b,c，可乐的价格是x(x是10的倍数)。
     * 如果游游优先使用大面额购买且钱是够的情况下,请计算出需要投入硬币次数？ 输入描述 依次输入， 需要可乐的数量为 m 10元的张数为 a 50元的张数为 b
     * 100元的张树为 c 1瓶可乐的价格为 x 输出描述 输出当前金额下需要投入硬币的次数
     * 例如需要购买2瓶可乐，每瓶可乐250圆，手里有100圆3枚，50圆4枚，10圆1枚。 购买第1瓶投递100圆3枚，找50圆 购买第2瓶投递50圆5枚
     * 所以是总共需要操作8次金额投递操作 样例输入 2 1 4 3 250 样例输出 8
     */

    // 暴力尝试，为了验证正式方法而已
    public static int right(int m, int a, int b, int c, int x) {
        int[] qian = { 100, 50, 10 };
        int[] zhang = { c, b, a };
        int puts = 0;
        while (m != 0) {
            int cur = buy(qian, zhang, x);
            if (cur == -1) {
                return -1;
            }
            puts += cur;
            m--;
        }
        return puts;
    }

    public static int buy(int[] qian, int[] zhang, int rest) {
        int first = -1;
        for (int i = 0; i < 3; i++) {
            if (zhang[i] != 0) {
                first = i;
                break;
            }
        }
        if (first == -1) {
            return -1;
        }
        if (qian[first] >= rest) {
            zhang[first]--;
            giveRest(qian, zhang, first + 1, qian[first] - rest, 1);
            return 1;
        } else {
            zhang[first]--;
            int next = buy(qian, zhang, rest - qian[first]);
            if (next == -1) {
                return -1;
            }
            return 1 + next;
        }
    }

    // 正式的方法
    // 要买的可乐数量，m
    // 100元有a张
    // 50元有b张
    // 10元有c张
    // 可乐单价x
    public static int putTimes(int m, int a, int b, int c, int x) {
        int[] money = {100, 50, 10};
        int[] nums = {c, b, a};
        // 总共需要多少次投币
        int puts = 0;
        // 之前面值的钱还剩下多少
        int preLeftMoney = 0;
        // 之前面值的钱还剩下多少张，具体个多少张无所谓
        int preLeftNum = 0;
        for (int i = 0; i < 3 && i != 0; i++) {
            // 当前面值参与搞定第一瓶可乐，需要掏出多少张
            // m/n向上取整 1.Math.floor 2.(m + (n - 1))/n
            int curParNumForFirstCola = (int) Math.ceil((x - preLeftMoney ) / money[i]);
            // int curParNumForFirstCola = (x - preLeftMoney + money[i] - 1) / money[i];
            if (nums[i] >= curParNumForFirstCola) { // 如果之前的钱和当前面值的钱，能凑出第一瓶可乐
                // 凑出来了一瓶可乐找的零钱加回给nums
                giveRest(money, nums, i + 1, (preLeftMoney + money[i] * curParNumForFirstCola) - x, 1);
                puts += curParNumForFirstCola + preLeftNum;
                nums[i] -= curParNumForFirstCola;
                m--;
            } else { // 如果之前的钱和当前面值的钱，不能凑出第一瓶可乐, 一起当作pre
                preLeftMoney += money[i] * nums[i];
                preLeftNum += nums[i];
                continue;
            }
            // 第一瓶处理完成 看当前面值还能买多少
            // 用当前面值的钱，买一瓶可乐需要几张
            int curParNumForPreCola = (int)Math.ceil(x / money[i]);
            // 用当前面值的钱，一共可以购买可乐的数量
            int ColaNumWithCurPar = Math.min(nums[i] / curParNumForPreCola, m);
            // 每次的找零（change）
            int PreChange = money[i] * curParNumForPreCola - x;
            // 每次买一瓶可乐，吐出的找零总钱数是PreChange
            // 一共买的可乐数是ColaNumWithCurPar，所以把零钱去提升后面几种面值的硬币数，
            // 就是giveRest的含义
            giveRest(money, nums, i + 1, PreChange, ColaNumWithCurPar);
            // 投了几次币
            puts += curParNumForPreCola * ColaNumWithCurPar;
            // 还剩下多少瓶可乐需要去搞定，继续用后面的面值搞定去吧
            m -= ColaNumWithCurPar;
            nums[i] -= curParNumForPreCola * ColaNumWithCurPar;
            preLeftMoney = money[i] * nums[i];
            preLeftNum = nums[i];
        }
        return m == 0 ? puts : -1;
    }

    public static void giveRest(int[] qian, int[] zhang, int i, int oneTimeRest, int times) {
        for (; i < 3; i++) {
            zhang[i] += (oneTimeRest / qian[i]) * times;
            oneTimeRest %= qian[i];
        }
    }

    public static void main(String[] args) {
        int testTime = 1000;
        int zhangMax = 10;
        int colaMax = 10;
        int priceMax = 20;
        System.out.println("如果错误会打印错误数据，否则就是正确");
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int m = (int) (Math.random() * colaMax);
            int a = (int) (Math.random() * zhangMax);
            int b = (int) (Math.random() * zhangMax);
            int c = (int) (Math.random() * zhangMax);
            int x = ((int) (Math.random() * priceMax) + 1) * 10;
            int ans1 = putTimes(m, a, b, c, x);
            int ans2 = right(m, a, b, c, x);
            if (ans1 != ans2) {
                System.out.println("int m = " + m + ";");
                System.out.println("int a = " + a + ";");
                System.out.println("int b = " + b + ";");
                System.out.println("int c = " + c + ";");
                System.out.println("int x = " + x + ";");
                break;
            }
        }
        System.out.println("test end");
    }
}
