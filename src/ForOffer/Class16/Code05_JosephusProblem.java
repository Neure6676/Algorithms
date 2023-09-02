package ForOffer.Class16;

/**
 * 约瑟夫环问题
 * 给定一个链表头节点head，和一个正数m，从头开始，每次数到m就杀死当前节点，
 * 然后被杀节点的下一个节点从1开始重新数，周而复始直到只剩一个节点，返回最后的节点
 * https://leetcode.com/problems/find-the-winner-of-the-circular-game/description/
 */
public class Code05_JosephusProblem {

    // 只要是"剃刀"型函数 用y=x%i来推
    // 杀之后的偏好推杀之前的编号 最后剩下的那个数编号是1
    public int findTheWinner(int n, int m) {
        if (n == 1) {
            return 1;
        }
        return (findTheWinner(n - 1, m) + m - 1) % n + 1;
    }
}
