package UltimateAlgo2024.StackAndQueue;

// https://leetcode.com/problems/design-circular-deque/description/
public class Code04_CircularDeque {

    class MyCircularDeque {

        int[] deque;
        int l, r, size, limit;

        public MyCircularDeque(int k) {
            deque = new int[k];
            l = 0;
            r = 0;
            size = 0;
            limit = k;
        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            if (isEmpty()) {
                l = r = 0;
                deque[l] = value;
            } else {
                l = l - 1 < 0 ? limit - 1 : l - 1;
                deque[l] = value;
            }
            size++;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            if (isEmpty()) {
                l = r = 0;
                deque[0] = value;
            } else {
                r = r + 1 == limit ? 0 : r + 1;
                deque[r] = value;
            }
            size++;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }

            l = l + 1 == limit ? 0 : l + 1;
            size--;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }

            r = r - 1 < 0 ? limit - 1 : r - 1;
            size--;
            return true;
        }

        public int getFront() {
            return isEmpty() ? -1 : deque[l];
        }

        public int getRear() {
            return isEmpty() ? -1 : deque[r];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }
    }

}
