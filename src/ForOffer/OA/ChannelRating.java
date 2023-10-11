package ForOffer.OA;

public class ChannelRating {

    public static int getChannelRating(int[] views) {
        int ans = 0;
        if (views == null || views.length < 3) {
            return ans;
        }
        int modVal = 1000000007;
        int N = views.length;

        for (int len = 3; len <= N; len++) {
            for (int s = 0; s <= N - len; s++) {
                int mid = 0;
                for (int c = s + 1; c <= s + len - 2; c++) {
                    mid ^= views[c];
                }
                ans += (views[s] ^ views[s + len - 1]) == mid ? 1 : 0;
            }
        }
        return ans % modVal;
    }


    public static void main(String[] args) {
        int[] views = {0, 3, 6, 5};
        System.out.println(getChannelRating(views));
    }
}
