package model;

public class Edge<T>{

    Vertex<T> from;
    Vertex<T> to;
    private int weight;

    public Edge(Vertex<T> vfrom, Vertex<T> vto, int weight) {

        this.from=vfrom;
        this.to=vto;
        this.weight = weight;
    }

    public Vertex<T> getFrom() {
        return from;
    }

    public void setFrom(Vertex<T> from) {
        this.from = from;
    }

    public Vertex<T> getTo() {
        return to;
    }

    public void setTo(Vertex<T> to) {
        this.to = to;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
