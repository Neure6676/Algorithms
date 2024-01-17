package UltimateAlgo2024.DataStructureDesign;

import java.util.HashMap;
import java.util.HashSet;

// https://leetcode.com/problems/insert-delete-getrandom-o1/
// 搞一个数组，下标连续，random一个下标
// 问题：多次调用remove后，数组不连续，影响random的效率
// 解决：每产生一个洞，用最后一个数字填上，记得同时更新map
public class Code03_InsertDeleteRandom {

    class RandomizedSet {

        static int[] arr = new int[200001];
        HashMap<Integer, Integer> map;
        int idx;

        public RandomizedSet() {
            map = new HashMap<>();
            idx = 0;
        }

        public boolean insert(int val) {
            if (!map.containsKey(val)) {
                map.put(val, idx);
                arr[idx++] = val;
                return true;
            }
            return false;
        }

        public boolean remove(int val) {
            if (map.containsKey(val)) {
                arr[map.get(val)] = arr[--idx];
                map.put(arr[map.get(val)], map.get(val));
                map.remove(val);
                return true;
            }
            return false;
        }

        public int getRandom() {
            if (idx == 0) {
                return -1;
            }
            return arr[(int) (Math.random() * idx)];
        }
    }

}
