package UltimateAlgo2024.DataStructureDesign;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
public class Code04_InsertDeleteRandomDuplicatesAllowed {

    class RandomizedCollection {

        static int[] arr = new int[200001];
        HashMap<Integer, Set<Integer>> map;
        int idx;

        public RandomizedCollection() {
            map = new HashMap<>();
            idx = 0;
        }


        public boolean insert(int val) {
            arr[idx] = val;
            Set<Integer> set = map.getOrDefault(val, new HashSet<Integer>());
            set.add(idx++);
            map.put(val, set);
            return set.size() == 1;
        }

        public boolean remove(int val) {
            if (map.containsKey(val)) {
                int replacedIdx = map.get(val).iterator().next();
                if (replacedIdx == idx - 1) {
                    arr[--idx] = Integer.MIN_VALUE;
                    map.get(val).remove(idx);
                    if (map.get(val).size() == 0) {
                        map.remove(val);
                    }
                    return true;
                }

                arr[replacedIdx] = arr[--idx];
                map.get(val).remove(replacedIdx);
                if (map.get(val).size() == 0) {
                    map.remove(val);
                }
                Set<Integer> set = map.get(arr[replacedIdx]);
                set.add(replacedIdx);
                set.remove(idx);
                map.put(arr[replacedIdx], set);
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
