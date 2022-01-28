import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Node{
    private int cost;
    private int number;
    private Color color;
    private String hashedName;
    ArrayList<Edge> connectedEdges = new ArrayList<>();
    ArrayList<Node> dijkstraResult = new ArrayList<>();


    public Node(int number)
    {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCost() {
        return cost;
    }

    public String getHashedName() {
        return hashedName;
    }

    public void setHashedName(String hashedName) {
        this.hashedName = hashedName;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public ArrayList<Edge> getConnectedEdges() {
        return connectedEdges;
    }

    public void addConnectedEdges(Edge newEdge) {
        this.connectedEdges.add(newEdge);
    }

    public ArrayList<Node> getDijkstraResult() {
        return dijkstraResult;
    }

    public void setDijkstraResult(ArrayList<Node> dijkstraResult) {
        this.dijkstraResult = dijkstraResult;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return getNumber() == node.getNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber());
    }
}