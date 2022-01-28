import java.util.*;

public class Main
{
    private static ArrayList<Node> dfsPath = new ArrayList<>();
    private static HashMap<Node, Integer> fairScores;
    public static void main(String[] args)
    {
        int flag = 0;
        Scanner sc = new Scanner(System.in);
        String nodeAndEdge = sc.nextLine();
        String[] words = nodeAndEdge.split(" ");
        int numOfNodes = Integer.parseInt(words[0]);
        int numOfEdges = Integer.parseInt(words[1]);

        String str = sc.nextLine();
        words = str.split(" ");

        Graph graph = new Graph();
        for (int i = 0; i < numOfNodes; i++) {
            Node node = new Node(Integer.parseInt(words[i]));
            String hash = HashClass.getMd5(words[i]);
            node.setHashedName(hash);
            graph.addNode(node);
        }

        for (int i = 0; i < numOfEdges; i++) {
            str = sc.nextLine();
            words = str.split(" ");

            int srcNumber = Integer.parseInt(words[0]);
            Node src = graph.getNodeFromNumber(srcNumber);
            int destNumber = Integer.parseInt(words[1]);
            Node dest = graph.getNodeFromNumber(destNumber);
            int weight = Integer.parseInt(words[2]);
            Edge edge = new Edge(src, dest, weight);
            graph.addEdges(edge);
            src.addConnectedEdges(edge);
            dest.addConnectedEdges(edge);
        }

        System.out.println("Menu:\n Choose each of these commands below." +
                " To finish the program enter exit\n" +
                "join [Node number]\n" +
                "left [Node number]\n" +
                "test (to see dfs result)");

        String command = sc.nextLine();
        words = command.split(" ");
        while (flag == 0)
        {
            switch (words[0]) {
                case "join":
                    graph = joinNode(Integer.parseInt(words[1]), graph);
                    fairFinder(graph);
                    break;
                case "left":
                    graph = leftNode(Integer.parseInt(words[1]), graph);
                    fairFinder(graph);
                    break;
                case "test":
                    test(graph);
                    break;
                case "exit":
                    flag = 1;
                    break;
            }
            System.out.println("Menu:\n Choose each of these commands below." +
                    " To finish the program enter exit" +
                    "join [Node number]" +
                    "left [Node number]" +
                    "test (to see dfs result)");
            command = sc.nextLine();
            words = command.split(" ");
        }


    }
    public static void test(Graph graph)
    {
        dfsPath = graph.DFS();
    }

    public static void dijkstra(Graph graph, int targetId, int srcId)
    {
        Dijkstra dijkstra = new Dijkstra(graph);
        Node targetNode = graph.getNodeFromNumber(targetId);
        int targetIndex = graph.getNodes().indexOf(targetNode);

        Node srcNode = graph.getNodeFromNumber(srcId);
        int srcIndex = graph.getNodes().indexOf(srcNode);

        dijkstra.execute(graph.getNodes().get(srcIndex));
        ArrayList<Node> path = dijkstra.getPath(graph.getNodes().get(targetIndex));
        System.out.println("Dijkstra path from " + graph.getNodes().get(targetIndex).getNumber() + " to " +
                graph.getNodes().get(0).getNumber() + " is ");
        if (path != null)
        {
            for (Node vertex : path) {
                System.out.print(vertex.getNumber() + " ");
            }
            System.out.println(" ");
            srcNode.setDijkstraResult(path);
        }
        else
            System.out.println(" { }");

    }

    public static void fairFinder(Graph graph)
    {
        fairScores = new HashMap<>();
        dfsPath = graph.DFS();
        for (Node cafeCandidate: dfsPath)
        {
            System.out.println("candidate cafe is: " + cafeCandidate.getNumber());
            for (Node sourcePoint : graph.getContactedNodes()) {
                if (!sourcePoint.equals(cafeCandidate)) {
                    dijkstra(graph, cafeCandidate.getNumber(), sourcePoint.getNumber());
                }
            }

            for (Node node : graph.getContactedNodes()) {
                for (int i = 0; i < node.getDijkstraResult().size() - 1; i++) {
                    for (Edge edge : graph.getEdges()) {
                        if ((edge.getSrc().equals(node.getDijkstraResult().get(i))
                                & edge.getDest().equals(node.getDijkstraResult().get(i + 1))) ||
                                (edge.getDest().equals(node.getDijkstraResult().get(i))
                                        & edge.getSrc().equals(node.getDijkstraResult().get(i + 1)))
                        ) {
                            System.out.println("source is: " + edge.getSrc().getNumber());
                            System.out.println("dest is: " + edge.getDest().getNumber());
                            System.out.println("edge cost is: " + edge.getWeight());
                            node.setCost(node.getCost() + edge.getWeight());
                            System.out.println("Node cost updated to " + node.getCost());
                        }
                    }
                }
            }
            int totalCost = 0;
            for (int i = 0; i < graph.getContactedNodes().size(); i++)
            {
                for (int j = 0; j < graph.getContactedNodes().size(); j++)
                {
                    totalCost += Math.abs(graph.getContactedNodes().get(i).getCost() -
                                 graph.getContactedNodes().get(j).getCost());
                }
            }
            totalCost = totalCost/6;
            fairScores.put(cafeCandidate, totalCost);

            for (Node node: graph.getContactedNodes())
            {
                node.setCost(0);
            }
        }

        Node key = Collections.min(fairScores.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println(key.getNumber());
    }
    public static Graph joinNode(int nodeNumber, Graph graph)
    {
        Node newNode = graph.getNodeFromNumber(nodeNumber);
        graph.addContactedNodes(newNode);
        return graph;
    }

    public static Graph leftNode(int nodeNumber, Graph graph)
    {
        Node newNode = graph.getNodeFromNumber(nodeNumber);
        graph.removeContactedNodes(newNode);
        return graph;
    }
}
