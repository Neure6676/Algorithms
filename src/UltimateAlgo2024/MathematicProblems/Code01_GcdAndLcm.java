package UltimateAlgo2024.MathematicProblems;

// Get greatest common divisor and least common multiple
public class Code01_GcdAndLcm {

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static long lcm(long a, long b) {
        return (long) a / gcd(a, b) * b;
    }

    public static void main(String[] args) {
        System.out.println(gcd(30, 50));
        System.out.println(lcm(30, 50));
    }
}
