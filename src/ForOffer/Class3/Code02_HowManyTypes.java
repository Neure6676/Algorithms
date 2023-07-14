package ForOffer.Class3;

import java.util.HashSet;

/**
 * 只由小写字母（a~z）组成的一批字符串，都放在字符类型的数组String[] arr中，如果其中某两个字符串所含有的字符种类完全一样
 * 就将两个字符串算作一类，比如baacbba和bac就算作一类，返回arr中有多少类
 *
 * 思路1：排序 去重 生成摘要 加到map中
 * 思路2：用一个整数表示摘要 看set中留下几个不同的整数
 */
public class Code02_HowManyTypes {

    public static int types(String[] arr) {
        HashSet<Integer> types = new HashSet<>();
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            char[] str = arr[i].toCharArray();
            int key = 0;
            int n = str.length;
            for (int j = 0; j < n; j++) {
                key |= (1 << str[j] - 'a');  //bit operation
            }
            types.add(key);
        }
        return types.size();
    }
}
