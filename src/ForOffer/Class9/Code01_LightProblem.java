package ForOffer.Class9;

/*
 * 给定一个数组arr，长度为N，arr中的值不是0就是1
 * arr[i]表示第i栈灯的状态，0代表灭灯，1代表亮灯
 * 每一栈灯都有开关，但是按下i号灯的开关，会同时改变i-1、i、i+2栈灯的状态
 * 问题一：
 * 如果N栈灯排成一条直线,请问最少按下多少次开关,能让灯都亮起来
 * 排成一条直线说明：
 * i为中间位置时，i号灯的开关能影响i-1、i和i+1
 * 0号灯的开关只能影响0和1位置的灯
 * N-1号灯的开关只能影响N-2和N-1位置的灯
 *
 * 问题二：
 * 如果N栈灯排成一个圈,请问最少按下多少次开关,能让灯都亮起来
 * 排成一个圈说明：
 * i为中间位置时，i号灯的开关能影响i-1、i和i+1
 * 0号灯的开关能影响N-1、0和1位置的灯
 * N-1号灯的开关能影响N-2、N-1和0位置的灯
 *
 * */
public class Code01_LightProblem {

    // 无环改灯问题的递归版本
    public static int noLoopMinStep1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] ^ 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        // 不变0位置的状态
        int p1 = process1(arr, 2, arr[0], arr[1]);
        // 改变0位置的状态
        int p2 = process1(arr, 2, arr[0] ^ 1, arr[1] ^ 1);
        if (p2 != Integer.MAX_VALUE) {
            p2++;
        }
        return Math.min(p1, p2);
    }

    // f(i+1, p, c)表示当前来到i位置做选择，p是i-1的状态，c是i的状态
    // 0到i-2是全亮
    public static int process1(int[] arr, int nextIndex, int preStatus, int curStatus) {
        if (nextIndex == arr.length) {  // 当前来到最后一个开关的位置
            return nextIndex != preStatus ? Integer.MAX_VALUE : (curStatus ^ 1);
        }
        // i < arr.length
        if (preStatus == 0) { // 当前必须摁开关
            curStatus ^= 1;
            // i + 1也变
            int nextStatus = arr[nextIndex] ^ 1;
            int choice = process1(arr, nextIndex + 1, curStatus, nextStatus);
            return choice == Integer.MAX_VALUE ? choice : (choice + 1);  // 加上当前这一次按
        } else {
            return process1(arr, nextIndex + 1, curStatus, arr[nextIndex]);
        }
    }


    // f(i+1, p, c, fs, es)表示当前来到i位置做选择，p是i-1的状态，c是i的状态, fs是0位置状态，es是N-1位置状态
    // 当前来到的位置(nextIndex - 1)，一定不能是1！至少从2开始
    // nextIndex >= 3
    public static int process2(int[] arr,
                               int nextIndex, int preStatus, int curStatus,
                               int endStatus, int firstStatus) {

        if (nextIndex == arr.length) { // 最后一按钮！
            return (endStatus != firstStatus || endStatus != preStatus) ? Integer.MAX_VALUE : (endStatus ^ 1);
        }
        // 当前位置，nextIndex - 1
        // 当前的状态，叫curStatus
        // 如果不按下按钮，下一步的preStatus, curStatus
        // 如果按下按钮，下一步的preStatus, curStatus ^ 1
        // 如果不按下按钮，下一步的curStatus, arr[nextIndex]
        // 如果按下按钮，下一步的curStatus, arr[nextIndex] ^ 1
        int noNextPreStatus = 0;
        int yesNextPreStatus = 0;
        int noNextCurStatus =0;
        int yesNextCurStatus = 0;
        int noEndStatus = 0;
        int yesEndStatus = 0;
        if(nextIndex < arr.length - 1) {// 当前没来到N-2位置
            noNextPreStatus = curStatus;
            yesNextPreStatus = curStatus ^ 1;
            noNextCurStatus = arr[nextIndex];
            yesNextCurStatus = arr[nextIndex] ^ 1;
        } else if(nextIndex == arr.length - 1) {// 当前来到的就是N-2位置
            noNextPreStatus = curStatus;
            yesNextPreStatus = curStatus ^ 1;
            noNextCurStatus = endStatus;
            yesNextCurStatus = endStatus ^ 1;
            noEndStatus = endStatus;
            yesEndStatus = endStatus ^ 1;
        }
        if(preStatus == 0) { // 必须按
            int next = process2(arr, nextIndex + 1, yesNextPreStatus, yesNextCurStatus,
                    nextIndex == arr.length - 1 ? yesEndStatus : endStatus, firstStatus); // 如果来到N-2位置，endStatus会变
            return next == Integer.MAX_VALUE ? next : (next + 1);
        }else {
            return process2(arr, nextIndex + 1, noNextPreStatus, noNextCurStatus,
                    nextIndex == arr.length - 1 ? noEndStatus : endStatus, firstStatus);

        }
    }

    // 有环改灯问题的递归版本
    public static int loopMinStep1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        if (arr.length == 3) {
            return (arr[0] != arr[1] || arr[0] != arr[2]) ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        // 0不变，1不变
        int p1 = process2(arr, 3, arr[1], arr[2], arr[arr.length - 1], arr[0]);
        // 0改变，1不变
        int p2 = process2(arr, 3, arr[1] ^ 1, arr[2], arr[arr.length - 1] ^ 1, arr[0] ^ 1);
        // 0不变，1改变
        int p3 = process2(arr, 3, arr[1] ^ 1, arr[2] ^ 1, arr[arr.length - 1], arr[0] ^ 1);
        // 0改变，1改变
        int p4 = process2(arr, 3, arr[1], arr[2] ^ 1, arr[arr.length - 1] ^ 1, arr[0]);
        p2 = p2 != Integer.MAX_VALUE ? (p2 + 1) : p2;
        p3 = p3 != Integer.MAX_VALUE ? (p3 + 1) : p3;
        p4 = p4 != Integer.MAX_VALUE ? (p4 + 2) : p4;
        return Math.min(Math.min(p1, p2), Math.min(p3, p4));
    }

}
