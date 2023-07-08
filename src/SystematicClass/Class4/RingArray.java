package SystematicClass.Class4;

public class RingArray {

    public static class MyQueue {
        private int[] arr;
        private int pushi;
        private int polli;
        private int size;  // 计数工具 不用通过pushi和polli计算
        private final int limit;

        public MyQueue(int limit) {
            arr = new int[limit];
            pushi = 0;
            polli = 0;
            size = 0;
            this.limit = limit;
        }

        public void offer(int value) {
            if (size == limit) {
                throw new IllegalStateException();
            }
            arr[pushi] = value;
            pushi = nextIndex(pushi);
            size++;
        }

        public int poll() {
            if (size == 0) {
                throw new IllegalStateException();
            }
            int ans = arr[polli];
            polli = nextIndex(polli);
            size --;
            return ans;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private int nextIndex(int index) {
            return index < limit -1 ? index + 1 : 0;
        }
    }

}
