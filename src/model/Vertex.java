package model;

import java.util.ArrayList;

public class Vertex<T>  {
    private int color;
    private T value;
    private int time1;
    private int time2;
    private int key;
    private int distance;
    private Vertex<T> padre;
    private ArrayList<Vertex<T>> adyacentes;
    protected ArrayList<Edge> aristas;


    public Vertex(T value, int key) {
        this.value = value;
        this.key = key;
        this.padre = null;
        adyacentes = new ArrayList<>();
        aristas = new ArrayList<>();

    }

    public void initialize() {

        this.aristas = new ArrayList<>();
        this.adyacentes = new ArrayList<>();

    }

    public void addAdj(Vertex<T> toAdd){
        adyacentes.add(toAdd);
    }

    public void addEdge(Edge<T> x){
        aristas.add(x);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public int getTime1() {
        return time1;
    }

    public void setTime1(int time1) {
        this.time1 = time1;
    }

    public int getTime2() {
        return time2;
    }

    public void setTime2(int time2) {
        this.time2 = time2;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Vertex<T> getPadre() {
        return padre;
    }

    public void setPadre(Vertex<T> padre) {
        this.padre = padre;
    }

    public ArrayList<Vertex<T>> getAdyacentes() {
        return adyacentes;
    }

    public void setAdyacentes(ArrayList<Vertex<T>> adyacentes) {
        this.adyacentes = adyacentes;
    }
    public void deleteAd(Vertex<T> vertex){
        for(int i = 0; i<adyacentes.size();i++){
            if (adyacentes.get(i).getValue().equals(vertex)){
                adyacentes.remove(vertex);
            }
        }

    }


    public void añadirAdyacente(Vertex<T> hijo) {
        adyacentes.add(hijo);
    }

    public void deleteArista(int from,int to){
        for (int i=0;i<aristas.size();i++){
            if(aristas.get(i).getFrom().getKey() == from && aristas.get(i).getFrom().getKey() == to){
                aristas.remove(i);
            }
        }
    }

    public boolean proveArista(int from,int to) {
        for (int i = 0; i < aristas.size(); i++) {
            if (aristas.get(i).getFrom().getKey() == from && aristas.get(i).getTo().getKey() == to) {
                return true;
            }
        }
        return false;
    }


}

