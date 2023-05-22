package model.graph;

import java.util.HashMap;
import java.util.stream.Collectors;

public class Vertex<K extends Comparable<K>, T> {
    private K key;
    private T element;
    private Color color;
    private final HashMap<K, Vertex<K, T>> adj;
    private final HashMap<K, Double> weight;

    public Vertex(K key, T element) {
        this.key = key;
        this.element = element;
        color = Color.WHITE;
        adj = new HashMap<>();
        this.weight = new HashMap<>();
    }

    public void addAdj(Vertex<K, T> newVertex, double weight) {
        validateVertexIsNotInAdj(newVertex);
        adj.put(newVertex.key, newVertex);
        this.weight.put(newVertex.key, weight);
    }

    private void validateVertexIsNotInAdj(Vertex<K, T> newVertex) {
        if (adj.get(newVertex.key) != null)
            throw new IllegalArgumentException("Vertex is already in the adjacency list: " + newVertex.key);
    }

    public String adjToString() {
        return adj.keySet().stream().map(Object::toString).collect(Collectors.joining(", "));
    }

    @Override
    public String toString() {
        return String.format("Key: %s, Element: %s, Adjacency: %s", key, element.toString(), adjToString());
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public HashMap<K, Vertex<K, T>> getAdj() {
        return adj;
    }

    public HashMap<K, Double> getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
