import java.awt.*;
import java.util.*;
import java.util.List;


class Graph {

    ArrayList<Node> nodes = new ArrayList<>();
    ArrayList<Node> contactedNodes = new ArrayList<>();
    ArrayList<Edge> edges = new ArrayList<>();
    private ArrayList<Node> dfsPath = new ArrayList<>();
    List<List<Node>> adj_list = new ArrayList<>();
    HashMap<Integer, Node> graphMap = new HashMap<>();

    public ArrayList<Node> getContactedNodes() {
        return contactedNodes;
    }

    public void addContactedNodes(Node node) {
        this.contactedNodes.add(node);
    }

    public void removeContactedNodes(Node node) {
        this.contactedNodes.remove(node);
    }

    public Node getNodeFromNumber(int number)
    {
        Iterator<Node> it = nodes.iterator();
        while(it.hasNext())
        {
            Node node = it.next();
            if (node.getNumber() == number)
                return node;
        }
        return null;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void addNode(Node newNode) {
        this.nodes.add(newNode);
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void addEdges(Edge edge) {
        this.edges.add(edge);
    }

    public static void printGraph(Graph graph)  {
        int src_vertex = 0;
        int list_size = graph.adj_list.size();

        System.out.println("The contents of the graph:");
        while (src_vertex < list_size) {
            for (Node edge : graph.adj_list.get(src_vertex)) {
                System.out.print("Vertex:" + src_vertex + " ==> " + edge.getNumber() );
            }

            System.out.println();
            src_vertex++;
        }
    }

    // CLRS DFS Algorithm
    public ArrayList<Node> DFS() {
        for (Node n : nodes) {
            n.setColor(Color.WHITE);
        }
        for (Node n : nodes) {
            if (n.getColor() == Color.WHITE) {
                printDFS(n);
            }
        }
        System.out.println(" ");
        return dfsPath;
    }

    public void printDFS(Node src)
    {
        src.setColor(Color.GRAY);
        for (Edge e : src.getConnectedEdges()) {
            Node dest = e.getDest();
            if (dest.getColor() == Color.WHITE) {
                printDFS(dest);
            }
        }
        src.setColor(Color.BLACK);
        System.out.print(src.getNumber() + " ");
        dfsPath.add(src);
    }

}
