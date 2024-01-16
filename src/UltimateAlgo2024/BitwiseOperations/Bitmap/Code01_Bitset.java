package UltimateAlgo2024.BitwiseOperations.Bitmap;


import java.util.HashSet;

// Bitset(int size)
// void add(int num)
// void remove(int num)
// void reverse(int num)
// boolean contains(int num)
public class Code01_Bitset {

    public static class Bitset {
        public int[] set;

        public Bitset(int n) {
            // n / 32 向上取整: (n + 32 - 1) / 32
            set = new int[(n + 31) / 32];
        }

        public void add(int num) {
            set[num / 32] |= 1 << (num % 32);
        }

        public void remove(int num) {
            set[num / 32] &= ~(1 << (num % 32));
        }

        public void reverse(int num) {
            set[num / 32] ^= 1 << (num % 32);
        }

        public boolean contains(int num) {
            return ((set[num / 32] >> (num % 32)) & 1) == 1;
        }

        // 对数器测试
        public static void main(String[] args) {
            int n = 1000;
            int testTimes = 10000;
            System.out.println("测试开始");
            // 实现的位图结构
            Bitset bitSet = new Bitset(n);
            // 直接用HashSet做对比测试
            HashSet<Integer> hashSet = new HashSet<>();
            System.out.println("调用阶段开始");
            for (int i = 0; i < testTimes; i++) {
                double decide = Math.random();
                // number -> 0 ~ n-1，等概率得到
                int number = (int) (Math.random() * n);
                if (decide < 0.333) {
                    bitSet.add(number);
                    hashSet.add(number);
                } else if (decide < 0.666) {
                    bitSet.remove(number);
                    hashSet.remove(number);
                } else {
                    bitSet.reverse(number);
                    if (hashSet.contains(number)) {
                        hashSet.remove(number);
                    } else {
                        hashSet.add(number);
                    }
                }
            }
            System.out.println("调用阶段结束");
            System.out.println("验证阶段开始");
            for (int i = 0; i < n; i++) {
                if (bitSet.contains(i) != hashSet.contains(i)) {
                    System.out.println("出错了!");
                }
            }
            System.out.println("验证阶段结束");
            System.out.println("测试结束");
        }


    }
}
