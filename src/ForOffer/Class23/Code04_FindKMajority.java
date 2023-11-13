package ForOffer.Class23;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 超级水王问题
 * 给定一个数组arr，长度为N，如果某个数出现次数大于N/2，称该数为水王数，如果arr中有水王数，打印这个数；如果没有水王数，打印没有水王数
 * 要求时间复杂度O(N)，额外空间复杂度O(1)
 * 扩展1：摩尔投票
 * 扩展2：给定一个正数K，返回所有出现次数>N/K的数
 */
public class Code04_FindKMajority {

    // 一次删掉两个不同值的数， 水王数一定会省下来！
    // 结果1: 没数剩下来：无水王数
    // 结果2: 有数剩下来：再遍历一次看出现次数够不够
    public static void printHalfMajor(int[] arr) {
        // 变量1: 候选；变量2: 血量
        // 规则：
        // 若无候选，当前数立为候选，血量=1
        // 若有候选，1. 当前数！=候选，血--  （当前数和靶子同时被删掉）
        //         2. 当前数==候选，血++    （再攒一个靶子）
        int cand = 0;
        int HP = 0;
        for (int num : arr) {
            if (HP == 0) {
                cand = num;
                HP = 1;
            } else if(num == cand) {
                HP++;
            } else {
                HP--;
            }
        }

        if(HP == 0) {
            System.out.println("No such number.");
        }

        int count = 0;
        for (int num : arr) {
            if (num == cand) {
                count++;
            }
        }
        if (HP > arr.length / 2) {
            System.out.println(cand);
        } else {
            System.out.println("No such number.");
        }
    }


    // 扩展2：给定一个正数K，返回所有出现次数>N/K的数
    // 一次删掉K个不同的数，准备一个K-1长度的候选表
    public static void printKMajor(int[] arr, int K) {
        if (K < 2) {
            System.out.println("the value of K is invalid.");
            return;
        }
        // 攒候选，cands，候选表，最多K-1条记录！ > N / K次的数字，最多有K-1个
        HashMap<Integer, Integer> cands = new HashMap<Integer, Integer>();
        for (int i = 0; i != arr.length; i++) {
            if (cands.containsKey(arr[i])) {
                cands.put(arr[i], cands.get(arr[i]) + 1);
            } else { // arr[i] 不是候选
                if (cands.size() == K - 1) { // 当前数肯定不要！，每一个候选付出1点血量，血量变成0的候选，要删掉！
                    allCandsMinusOne(cands);
                } else {
                    cands.put(arr[i], 1);
                }
            }
        }
        // 所有可能的候选，都在cands表中！遍历一遍arr，每个候选收集真实次数
        HashMap<Integer, Integer> reals = getReals(arr, cands);
        boolean hasPrint = false;
        for (Map.Entry<Integer, Integer> set : cands.entrySet()) {
            Integer key = set.getKey();
            if (reals.get(key) > arr.length / K) {
                hasPrint = true;
                System.out.print(key + " ");
            }
        }
        System.out.println(hasPrint ? "" : "no such number.");
    }

    public static void allCandsMinusOne(HashMap<Integer, Integer> map) {
        List<Integer> removeList = new LinkedList<Integer>();
        for (Map.Entry<Integer, Integer> set : map.entrySet()) {
            Integer key = set.getKey();
            Integer value = set.getValue();
            if (value == 1) {
                removeList.add(key);
            }
            map.put(key, value - 1);
        }
        // 不能边迭代边删Key，先收集起来最后一起删除
        for (Integer removeKey : removeList) {
            map.remove(removeKey);
        }
    }

    public static HashMap<Integer, Integer> getReals(int[] arr,
                                                     HashMap<Integer, Integer> cands) {
        HashMap<Integer, Integer> reals = new HashMap<Integer, Integer>();
        for (int i = 0; i != arr.length; i++) {
            int curNum = arr[i];
            if (cands.containsKey(curNum)) {
                if (reals.containsKey(curNum)) {
                    reals.put(curNum, reals.get(curNum) + 1);
                } else {
                    reals.put(curNum, 1);
                }
            }
        }
        return reals;
    }
}
