package ForOffer.Class2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * 给定数组hard和money，长度都为N，hard[i]表示i号工作的难度， money[i]表示i号工作的收入
 * 给定数组ability，长度都为M，ability[j]表示j号人的能力，每一号工作，都可以提供无数的岗位，难度和收入都一样
 * 但是人的能力必须>=这份工作的难度，才能上班。返回一个长度为M的数组ans，ans[j]表示j号人能获得的最好收入
 */
public class Code01_ChooseWork {

    // 根据难度排序 难度小的放前面 难度一样的收入多的放前面
    // 同样难度收入少的可直接删掉
    // 难度增加但薪水少的可直接删掉
    // 此时就是难度上升 收入必上升 -> 二分
    // 用什么结构查询-> 有序表：O(logN) 快速查询大于等于或小于等于它且最近的

    public static class Job {
        public int hard;
        public int money;

        public Job(int h, int m) {
            hard = h;
            money = m;
        }
    }

    // 比较器 返回负数时o1在前
    public static class JobComparator implements Comparator<Job> {
        @Override
        public int compare(Job o1, Job o2) {
            return o1.hard != o2.hard ? (o1.hard - o2.hard) : (o2.money - o1.money);
        }
    }

    public static int[] getMoneys(Job[] job, int[] ability) {
        Arrays.sort(job, new JobComparator());
        // key: hard  value: money
        TreeMap<Integer, Integer> map = new TreeMap<>();
        // 删工作 使难度上升 收入必上升
        map.put(job[0].hard, job[0].money);
        // pre: 上一份"进入map"的工作
        Job pre = job[0];
        for (int i = 1; i < job.length; i++) {
            if (job[i].hard != pre.hard && job[i].money > pre.money) {
                map.put(job[i].hard, job[i].money);
                pre = job[i];
            }
        }
        int[] ans = new int[ability.length];
        for (int i = 0; i < ability.length; i++) {
            // 当前工作的hard <= ability[i]  且离它最近的
            // ans[i] = map.get(map.floorKey(ability[i]));
            Integer key = map.floorKey(ability[i]);
            ans[i] = key != null ? map.get(key) : 0;
        }
        return ans;
    }
}
