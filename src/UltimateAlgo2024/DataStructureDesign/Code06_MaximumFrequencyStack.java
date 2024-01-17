package UltimateAlgo2024.DataStructureDesign;

import java.util.ArrayList;
import java.util.HashMap;

// https://leetcode.com/problems/maximum-frequency-stack/
// 词频表+最大频率
public class Code06_MaximumFrequencyStack {

    class FreqStack {

        private int topTimes;

        // 每层节点
        private HashMap<Integer, ArrayList<Integer>> stack;

        // 每一个数出现了几次
        private HashMap<Integer, Integer> freqMap;

        public FreqStack() {
            topTimes = 0;
            stack = new HashMap<>();
            freqMap = new HashMap<>();
        }

        public void push(int val) {
            int newFreq = freqMap.getOrDefault(val, 0) + 1;
            freqMap.put(val, newFreq);
            ArrayList<Integer> list = stack.getOrDefault(newFreq, new ArrayList<>());
            list.add(val);
            stack.put(newFreq, list);
            topTimes = Math.max(topTimes, newFreq);
        }

        public int pop() {
            if (stack == null) {
                return -1;
            }
            ArrayList<Integer> list = stack.get(topTimes);
            int ans = list.remove(list.size() - 1);
            if (list.isEmpty()) {
                stack.remove(topTimes--);
            }
            int freq = freqMap.get(ans);
            if (freq == 1) {
                freqMap.remove(ans);
            } else {
                freqMap.put(ans, freq - 1);
            }
            return ans;
        }
    }




}
