package ForOffer.OA;

import java.util.HashMap;

public class ChannelRating {

    // O(N)
    public static int getChannelRating(int[] views) {
        int ans = 0;
        if (views == null || views.length < 3) {
            return ans;
        }

        int modVal = 1000000007;
        int N = views.length;
        HashMap<Integer, Integer> doc = new HashMap<>();
        int[] preXor = new int[N];

        preXor[0] = views[0];
        for (int i = 1; i < N; i++) {
            preXor[i] = preXor[i - 1] ^ views[i];
        }
        doc.put(0, 1);

        for (int i = 0; i < N; i++) {
            if (doc.containsKey(preXor[i])) {
                ans += doc.get(preXor[i]);
            }
            doc.put(preXor[i], doc.getOrDefault(preXor[i], 0) + 1);
        }

        // delete case 1: length is 1
        // delete case 2: length is 2
        for (int i = 0; i < N; i++) {
            if (views[i] == 0) {
                ans--;
            }
            if (i > 0) {
                if (views[i] == views[i - 1]) {
                    ans--;
                }
            }
        }

        return ans % modVal;
    }

//    public static void main(String[] args) {
//        System.out.println(getChannelRating(new int[] {0, 3, 6, 5}));
//        System.out.println(comparator(new int[] {0, 3, 6, 5}));
//    }

//    // O(n^2)
    public static int comparator(int[] views) {
        int ans = 0;
        if (views == null || views.length < 3) {
            return ans;
        }
        int modVal = 1000000007;
        int N = views.length;
        int[] xor = new int[N];

        xor[0] = views[0];
        for (int i = 1; i < N; i++) {
            xor[i] = xor[i - 1] ^ views[i];
        }

        for (int start = 0; start <= N - 3; start++) {
            for (int end = start + 2; end < N; end++) {
                if (start == 0) {
                    ans += xor[end] == 0 ? 1 : 0;
                } else {
                    ans += (xor[end] ^ xor[start - 1]) == 0 ? 1 : 0;
                }
            }
        }
        return ans % modVal;
    }


    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 10000;
        int maxSize = 1000;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int res = getChannelRating(arr);
            int comp = comparator(arr);
            if (res != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
