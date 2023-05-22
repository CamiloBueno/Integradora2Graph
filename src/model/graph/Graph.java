package model.graph;

import java.util.*;

public class Graph<K extends Comparable<K>, T> implements IGraph<K> {
    private final boolean isDirected;
    private final HashMap<K, Vertex<K, T>> vertexes;

    public Graph(boolean isDirected) {
        this.isDirected = isDirected;
        vertexes = new HashMap<>();
    }

    public void addVertex(K key, T value) {
        Vertex<K, T> newVertex = new Vertex<>(key, value);
        vertexes.put(key, newVertex);
    }

    public void addEdge(K vStart, K vFinal, double weight) {
        validateVertexExist(vStart);
        validateVertexExist(vFinal);
        Vertex<K, T> vertexStart = vertexes.get(vStart);
        Vertex<K, T> vertexFinal = vertexes.get(vFinal);
        vertexStart.addAdj(vertexFinal, weight);
        if (!isDirected)
            vertexFinal.addAdj(vertexStart, weight);
    }

    private void validateVertexExist(K vertexKey) {
        if (vertexes.get(vertexKey) == null)
            throw new IllegalArgumentException("Vertex doesn't exists: " + vertexKey);
    }

    public HashMap<K, Vertex<K, T>> getVertexes() {
        return vertexes;
    }

    public String showVertexInfo(K key) {
        validateVertexExist(key);
        return vertexes.get(key).toString();
    }

    public String showAdjList(K key) {
        validateVertexExist(key);
        return vertexes.get(key).adjToString();
    }

    @Override
    public String bfs(K vStart) {
        validateVertexExist(vStart);
        StringBuilder sb = new StringBuilder();
        Queue<Vertex<K, T>> queue = new ArrayDeque<>();
        vertexes.get(vStart).setColor(Color.GRAY);
        queue.add(vertexes.get(vStart));
        while (!queue.isEmpty()) {
            Vertex<K, T> temp = queue.poll();
            sb.append(String.format("%s ", temp.getKey()));
            for (K key : temp.getAdj().keySet()) {
                if (vertexes.get(key).getColor() == Color.WHITE) {
                    vertexes.get(key).setColor(Color.GRAY);
                    queue.add(vertexes.get(key));
                }
            }
        }
        resetColors();
        return sb.toString().trim();
    }

    private void resetColors() {
        vertexes.forEach((key, value) -> value.setColor(Color.WHITE));
    }

    @Override
    public String dfs(K vStart) {
        validateVertexExist(vStart);
        StringBuilder sb = new StringBuilder();
        dfsUtil(vertexes.get(vStart), sb);
        resetColors();
        return sb.toString().trim();
    }

    private void dfsUtil(Vertex<K, T> vertex, StringBuilder sb) {
        vertexes.get(vertex.getKey()).setColor(Color.GRAY);
        sb.append(String.format("%s ", vertex.getKey()));
        for (K key : vertex.getAdj().keySet()) {
            if (vertexes.get(key).getColor() == Color.WHITE)
                dfsUtil(vertexes.get(key), sb);
        }
    }

    public HashMap<K, Double> dijkstra(Vertex<K, T> initVertex) {
        HashMap<K, Double> distances = new HashMap<>();
        for (K key : vertexes.keySet()) {
            distances.put(key, Double.MAX_VALUE);
        }
        distances.put(initVertex.getKey(), 0.0);
        for (int i = 0; i < vertexes.keySet().size() - 1; i++) {
            K minDistanceNode = findMinDistanceNode(distances);
            vertexes.get(minDistanceNode).setColor(Color.GRAY);
            HashMap<K, Double> neighbors = vertexes.get(minDistanceNode).getWeight();
            for (K key : neighbors.keySet()) {
                double newDistance = distances.get(minDistanceNode) + neighbors.get(key);
                if (vertexes.get(key).getColor() == Color.WHITE && newDistance < distances.get(key))
                    distances.put(key, newDistance);
            }
        }
        return distances;
    }

    private K findMinDistanceNode(HashMap<K, Double> distances) {
        double minDistance = Double.MAX_VALUE;
        K minDistanceNode = null;
        for (K key : vertexes.keySet()) {
            if (vertexes.get(key).getColor() == Color.WHITE && distances.get(key) < minDistance) {
                minDistance = distances.get(key);
                minDistanceNode = key;
            }
        }
        return minDistanceNode;
    }
}