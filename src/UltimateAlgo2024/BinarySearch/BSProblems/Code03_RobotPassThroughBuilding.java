package UltimateAlgo2024.BinarySearch.BSProblems;

// https://www.nowcoder.com/practice/7037a3d57bbd4336856b8e16a9cafd71
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Code03_RobotPassThroughBuilding {

    public static int MAXN = 100001;

    public static int[] arr = new int[MAXN];

    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            int l = 0;
            int r = 0;
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
                r = Math.max(r, arr[i]);
            }
            out.println(compute(l, r, r));
        }
        out.flush();
        out.close();
        br.close();
    }

    // [l,r]通关所需最小能量的范围，不停二分
    // max是所有建筑的最大高度
    // 时间复杂度O(n * log(max))，额外空间复杂度O(1)
    public static int compute(int l, int r, int max) {
        int m, ans = -1;
        while (l <= r) {
            // m中点，此时通关所需规定的初始能量
            m = l + ((r - l) >> 1);
            if (f(m, max)) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    // 初始能量为energy，max是建筑的最大高度，返回能不能通关
    // 为什么要给定建筑的最大高度？
    public static boolean f(int energy, int max) {
        // 注意！
        // 如果给的能量值很大，那么后续能量增长将非常恐怖
        // 完全有可能超出long的范围
        // 所以要在遍历时，一定要加入energy >= max的判断
        // 一旦能量超过高度最大值，后面肯定通关了，可以提前返回了
        // 这里很阴
        for (int i = 1; i <= n; i++) {
            if (energy <= arr[i]) {
                energy -= arr[i] - energy;
            } else {
                energy += energy - arr[i];
            }
            if (energy >= max) {
                return true;
            }
            if (energy < 0) {
                return false;
            }
        }
        return true;
    }

}
