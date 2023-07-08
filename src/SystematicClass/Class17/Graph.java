package SystematicClass.Class17;

import java.util.HashMap;
import java.util.HashSet;

// 图由点集和边集组成
public class Graph {
    public HashMap<Integer, Node> nodes; // Integer is value of Node
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
