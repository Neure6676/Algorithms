package UltimateAlgo2024.Graph.Part2;

// https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting/
// 1.小环 ==2 找两边各自能延伸的长度
// 2.大环 >=3 最大大环长度
public class Code04_MaximumEmployeesToBeInvitedToAMeeting {
    public int maximumInvitations(int[] favorite) {
        // favorite就可以表示图
        int n = favorite.length;
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            indegree[favorite[i]]++;
        }
        int[] queue = new int[n];
        int l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue[r++] = i;
            }
        }
        // 推消息（深度）
        // depth[i]: 不包括i在内，i之前最长链长度
        int[] depth = new int[n];
        while (l < r) {
            int cur = queue[l++];
            int next = favorite[cur];
            depth[next] = Math.max(depth[next], depth[cur] + 1);
            if (--indegree[next] == 0) {
                queue[r++] = next;
            }
        }
        // 目前只剩环
        // 可能性1 : 所有小环(中心个数 == 2)，算上中心点 + 延伸点，总个数
        int sumOfSmallRings = 0;
        // 可能性2 : 所有大环(中心个数 > 2)，只算中心点，最大环的中心点个数
        int bigRings = 0;
        for (int i = 0; i < n; i++) {
            // 只关心环
            if (indegree[i] > 0) {
                int ringSize = 1;
                indegree[i] = 0;
                for (int j = favorite[i]; j != i; j = favorite[j]) {
                    ringSize++;
                    indegree[j] = 0;
                }
                if (ringSize == 2) {
                    sumOfSmallRings += 2 + depth[i] + depth[favorite[i]];
                } else {
                    bigRings = Math.max(bigRings, ringSize);
                }
            }
        }
        return Math.max(sumOfSmallRings, bigRings);
    }

}
