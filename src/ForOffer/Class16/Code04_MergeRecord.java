package ForOffer.Class16;

/*
 * 腾讯原题
 *
 * 给定整数power，给定一个数组arr，给定一个数组reverse。含义如下：
 * arr的长度一定是2的power次方，reverse中的每个值一定都在0~power范围。
 * 例如power = 2, arr = {3, 1, 4, 2}，reverse = {0, 1, 0, 2}
 *
 * 任何一个在前的数字可以和任何一个在后的数组，构成一对数。可能是升序关系、相等关系或者降序关系。
 * 比如arr开始时有如下的降序对：(3,1)、(3,2)、(4,2)，一共3个。
 * 接下来根据reverse对arr进行调整：
 * reverse[0] = 0, 表示在arr中，划分每1(2的0次方)个数一组，然后每个小组内部逆序，那么arr变成
 * [3,1,4,2]，此时有3个逆序对。
 * reverse[1] = 1, 表示在arr中，划分每2(2的1次方)个数一组，然后每个小组内部逆序，那么arr变成
 * [1,3,2,4]，此时有1个逆序对
 * reverse[2] = 0, 表示在arr中，划分每1(2的0次方)个数一组，然后每个小组内部逆序，那么arr变成
 * [1,3,2,4]，此时有1个逆序对。
 * reverse[3] = 2, 表示在arr中，划分每4(2的2次方)个数一组，然后每个小组内部逆序，那么arr变成
 * [4,2,3,1]，此时有5个逆序对。
 * 所以返回[3,1,1,5]，表示"每次调整之后的逆序对数量"。
 *
 * 输入数据状况：
 * power的范围[0,20]
 * arr长度范围[1,10的7次方]
 * reverse长度范围[1,10的6次方]
 *
 * */

public class Code04_MergeRecord {

    public static void reversePart(int[] arr, int L, int R) {
        while (L < R) {
            int tmp = arr[L];
            arr[L++] = arr[R];
            arr[R--] = tmp;
        }
    }

    public static int[] copyArray(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static int[] reversePair(int[] originArr, int[] reverseArr, int power) {
        // 用merge sort的思路求分别以2的1，2，3。。。次方一组时正序逆序的数量
        // 把求正序数量转化为reverse上求逆序数量
        int[] reverse = copyArray(originArr);
        reversePart(reverse, 0, reverse.length - 1);
        int[] recordDown = new int[power + 1]; // 逆序
        int[] recordUp = new int[power + 1]; // 正序
        // 填入这两个数组
        process(originArr, 0, originArr.length - 1, power, recordDown);
        process(reverse, 0, reverse.length - 1, power, recordUp);
        int[] ans = new int[reverseArr.length];
        for (int i = 0; i < reverseArr.length; i++) {
            int curPower = reverseArr[i];
            for (int p = 1; p <=curPower; p++) {
                int tmp = recordDown[p];
                recordDown[p] = recordUp[p];
                recordUp[p] = tmp;
            }
            for (int p = 1; p <= power; p++) {
                ans[i] += recordDown[p];
            }
        }
        return ans;

    }

    // L...R完成排序
    // power是这段的长度
    public static void process(int[] originArr, int L, int R, int power, int[] record) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) / 2);
        process(originArr, L, mid, power - 1, record);
        process(originArr, mid + 1, R, power - 1, record);
        record[power] += merge(originArr, L, mid, R);
    }

    public static int merge(int[] arr, int L, int m, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = m + 1;
        int ans = 0;
        while (p1 <= m && p2 <= R) {
            ans += arr[p1] > arr[p2] ? (m - p1 + 1) : 0;
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= m) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }

}
