package model.graph;

public interface IGraph<K> {
    String bfs(K key);
    String dfs(K key);
}
