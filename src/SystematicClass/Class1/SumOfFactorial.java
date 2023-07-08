package SystematicClass.Class1;

public class SumOfFactorial {
    public static long calSumOfFactorial(int N) {
        long ans = 0;
        long cur = 1;
        for (int i = 1; i <= N; i++) {
            cur = cur * i;
            ans += cur;
        }
        return ans;
    }


    public static void main(String[] args) {
        int N = 10;
        System.out.println(calSumOfFactorial(N));
    }
}
