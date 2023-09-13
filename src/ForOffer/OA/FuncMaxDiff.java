package ForOffer.OA;

public class FuncMaxDiff {

    public static int funcMaxDiff(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int min = arr[0];
        int ans = 0;
        for (int i = 1; i < N; i++) {
            min = Math.min(arr[i], min);
            ans = Math.max(ans, arr[i] - min);
        }
        return ans;
    }

    public static void main (String[] args) {
        int[] arr = {11, 2, 10, 6, 4, 8, 1};
        System.out.println(funcMaxDiff(arr));
    }


}
