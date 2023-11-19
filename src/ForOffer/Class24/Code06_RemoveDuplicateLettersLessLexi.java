package ForOffer.Class24;

// https://leetcode.com/problems/remove-duplicate-letters/
public class Code06_RemoveDuplicateLettersLessLexi {

    public String removeDuplicateLetters(String s) {
        int[] count = new int[26];
        boolean[] isEntered = new boolean[26];
        int N = s.length();
        for (int i = 0; i < N; i++) {
            count[s.charAt(i) - 'a']++;
        }
        // 单调栈
        // 从左到右只保留依次变大的字符
        // 比如: a c e....
        char[] stack = new char[26];
        int size = 0;  // 此时stack的容量 stack.isEmpty()
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            // 如果cur已经在单调栈里，不进入!
            // 因为单调栈里每种字符只保留一个
            if (!isEntered[cur - 'a']) {
                isEntered[cur - 'a'] = true;
                // !如果一个字符要弹出，但是后面已经没有这种字符了，不能弹出!
                // 因为一旦弹出，再也没有机会收集到这种字符了！因为后面没有了
                while (size > 0 && stack[size - 1] > cur && count[stack[size - 1] - 'a'] > 0) {
                    // 这种字符要弹出了，所以标记这种字符出去了
                    isEntered[stack[size - 1] - 'a'] = false;
                    // 单调栈大小缩减了，也就是弹出了
                    size--;
                }
                // 当前字符进入了单调栈
                stack[size++] = cur;
            }
            // 当前字符的词频调整，调整后表示后面还有多少个当前字符
            // 注意词频表更新
            count[cur - 'a']--;
        }
        // 单调栈里的字符，拼字符串返回
        return String.valueOf(stack, 0, size);
    }
}
