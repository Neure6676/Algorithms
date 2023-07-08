package SystematicClass.Class17;

public class GraphGenerator {

    //输入[weight, from节点的值, to节点的值]
    public static Graph createGraph(Integer[][] matrix) {   //也可以用int
        Graph graph = new Graph();
        for (int i = 1; i < matrix.length; i++) {
            Integer weight = matrix[i][0];
            Integer from = matrix[i][1];
            Integer to = matrix[i][2];
            if (!graph.nodes.containsKey(from)) { // 如果图中不包含节点，构建出来
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);  //把这两个点拿出来
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);
            //善后工作
            fromNode.nexts.add(toNode); //form的直接邻居包含to
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }
}
