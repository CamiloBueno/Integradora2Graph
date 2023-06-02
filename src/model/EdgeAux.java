package model;

public class EdgeAux{

    int from;
    int to;
    private int weight;

    public EdgeAux(int vfrom, int vto, int weight) {

        this.from=vfrom;
        this.to=vto;
        this.weight = weight;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
