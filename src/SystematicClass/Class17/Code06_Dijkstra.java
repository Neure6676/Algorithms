package SystematicClass.Class17;

import java.util.HashMap;
import java.util.HashSet;

// no negative weight
public class Code06_Dijkstra {

    public static HashMap<Node, Integer> dijkstra(Node from) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);  //起始点到自己距离是0
        HashSet<Node> selectedNodes = new HashSet<>();  // 存打了对钩的Node
        // distance map中的所有记录（除去打过对号的），把距离最小的点拿出来
        // 第一次调用时一定是from
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        while (minNode != null) {
            // 原始点from到跳转点minNode的最小距离
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to; // 每条边的终点
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, distance + edge.weight);
                } else {
                    distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            selectedNodes.add(minNode); // 看完它所有邻居就给他打勾
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    private static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNodes) {

        return null;
    }
}
