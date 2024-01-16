package UltimateAlgo2024.BitwiseOperations.Bitmap;

// https://leetcode.com/problems/design-bitset/
public class Code02_DesignBitsetTest {

    public class Bitset{

        private int[] set;
        private final int size;
        private int zeros;
        private int ones;
        private boolean reverse; // 如果reverse = true: 则1:不存在， 0:存在

        public Bitset(int n) {
            set = new int[(n - 31) / 32];
            size = n;
            zeros = n;
            ones = n;
            reverse = false;
        }

        public void fix(int idx) {
            int index = idx /32;
            int bits = idx % 32;
            if (!reverse) {
                // 1:存在， 0:no存在
                if ((set[idx] & (1 << bits)) == 0) {
                    set[idx] |= (1 << bits);
                    zeros--;
                    ones++;
                }
            } else {
                // 1:不存在， 0:存在
                if ((set[idx] & (1 << bits)) != 0) {
                    set[idx] &= ~(1 << bits);
                    zeros--;
                    ones++;
                }
            }
        }

        public void unfix(int idx) {
            int index = idx /32;
            int bits = idx % 32;
            if (!reverse) {
                // 1:存在， 0:no存在
                if ((set[idx] & (1 << bits)) == 1) {
                    set[idx] &= ~(1 << bits);
                    zeros++;
                    ones--;
                }
            } else {
                // 1:不存在， 0:存在
                if ((set[idx] & (1 << bits)) != 1) {
                    set[idx] |= (1 << bits);
                    zeros++;
                    ones--;
                }
            }
        }

        public void flip() {
            reverse = !reverse;
            int temp = zeros;
            zeros = ones;
            ones = temp;
        }

        public boolean all() {
            return ones == size;
        }

        public boolean one() {
            return ones != 0;
        }

        public int count() {
            return ones;
        }

        public String toString() {
            StringBuilder str = new StringBuilder();
            for (int idx = 0; idx < set.length ;idx++) {
                for (int bit  = 0; bit < 32; bit++) {
                    int num = (set[idx] >> bit) & 1;
                    num ^= reverse ? 1 : 0;
                    str.append(num);
                }
            }
            return str.toString();
        }

    }


}
