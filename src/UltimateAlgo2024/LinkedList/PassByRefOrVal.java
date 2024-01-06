package UltimateAlgo2024.LinkedList;

public class PassByRefOrVal {

    public static void main(String[] args) {
        // int、long、byte、short
        // char、float、double、boolean
        // and String
        // Pass by value with Primitives
        int a = 10;
        f(a);
        System.out.println(a);

        // Pass by value with reference types
        Number b = new Number(5);
        g1(b);
        System.out.println(b.val);
        g2(b);
        System.out.println(b.val);

        // and array
        int[] c = { 1, 2, 3, 4 };
        g3(c);
        System.out.println(c[0]);
        g4(c);
        System.out.println(c[0]);
    }

    public static void f(int a) {
        a = 0;
    }

    public static class Number {
        public int val;

        public Number(int v) {
            val = v;
        }
    }

    public static void g1(Number b) {
        b = null;
    }

    public static void g2(Number b) {
        b.val = 6;
    }

    public static void g3(int[] c) {
        c = null;
    }

    public static void g4(int[] c) {
        c[0] = 100;
    }
}
