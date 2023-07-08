package SystematicClass.Class17;

import java.util.ArrayList;

public class Node {
    public int value;
    public int in;
    public int out;
    public ArrayList<Node> nexts; // 从该点出发能到哪几个点
    public ArrayList<Edge> edges; // 从该点出发有哪些边

    public Node(int _value) {
        value = _value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
