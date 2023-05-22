import model.graph.Color;
import model.graph.Graph;
import model.graph.Vertex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class GraphTest {
    private Graph<Integer, String> graph;
    private Graph<Integer, Integer> graph2;
    private Graph<String, Integer> graph3;

    @BeforeEach
    public void setUp() {
        graph = new Graph<>();
        graph2 = new Graph<>();
        graph3 = new Graph<>();
    }

    @Test
    public void testAddNode() {
        graph.addNode(1, "Kurt");
        graph.addNode(2, "Karl");

        String adjList1 = graph.showAdjList(1);
        String adjList2 = graph.showAdjList(2);

        //No debe mostrar nada ya que no se han conectado los vertices
        Assertions.assertEquals("", adjList1);
        Assertions.assertEquals("", adjList2);
    }

    @Test
    public void testAddNode2() {
        graph.addNode(1, "Alex");
        graph.addNode(2, "Michel");
        graph.addNode(14, "Karl");
        graph.addNode(30, "Kurt");

        graph.connectVertex(1, 2, 2, 40.0);
        graph.connectVertex(2, 14, 2, 20.0);
        graph.connectVertex(14, 30, 2, 70.0);
        graph.connectVertex(1, 14, 2, 25.0);

        HashMap<Integer,Double> distances = graph.dijkstra(graph.getHashMapVertexes().get(1));
        Assertions.assertEquals(25,distances.get(14));
        Assertions.assertEquals(40,distances.get(2));
        Assertions.assertEquals(95,distances.get(30));

    }

    /*
    @Test
    public void testAddNode3() {
        graph2.addNode(40, 2045);
        graph2.addNode(34, 2056);

        graph2.connectVertex(40, 40, 3);
        graph2.connectVertex(40, 40, 3);
        graph2.connectVertex(40, 34, 3); //Aquí no debería agregar ningún vértice a ninguna lista de adyacencia, ya que al ser bucle, el vStart y vFinal deben ser iguales

        String adjList1 = graph2.showAdjList(40);
        String adjList2 = graph2.showAdjList(34);

        Assertions.assertEquals("[2045][2045][2056]", adjList1);
        Assertions.assertEquals("", adjList2);
    }

    @Test
    public void testConnectVertex() {
        graph.addNode(1, "What");
        graph.addNode(2, "hey");
        graph.addNode(3, "which");

        String result1 = graph.connectVertex(1, 2, 1); // Simple Graph
        String result2 = graph.connectVertex(1, 2, 2); // Multigrafo
        String result3 = graph.connectVertex(1, 2, 3); // Loop/multigraph
        String result4 = graph.connectVertex(1, 3, 4); // Directed

        Assertions.assertEquals("Connected simple Vertices", result1);
        Assertions.assertEquals("multigraph Vertices connected", result2);
        Assertions.assertEquals("loops/multigraph Vertices connected", result3);
        Assertions.assertEquals("directed Vertices connected", result4);
    }

    @Test
    public void testConnectVertex2() {
        graph.addNode(1, "machine");
        graph.addNode(2, "tiger");
        graph.addNode(3, "brick");

        String result = graph.connectVertex(1, 2, 2); // Multigraph

        Assertions.assertEquals("multigraph Vertices connected", result);

        // conectamos los mismos vertices y no debe arrojar error, ya que es multigrafo
        result = graph.connectVertex(1, 2, 2); // Multigraph

        Assertions.assertEquals("multigraph Vertices connected", result);
    }

    @Test
    public void testConnectVertex3() {
        graph.addNode(1, "Gold");
        graph.addNode(2, "Silver");
        graph.addNode(3, "Bronze");

        String result1 = graph.connectVertex(1, 1, 3); // Loop/multigraph
        String result2 = graph.connectVertex(2, 2, 3); // Loop/multigraph

        Assertions.assertEquals("loops/multigraph Vertexes connected", result1);
        Assertions.assertEquals("loops/multigraph Vertexes connected", result2);
    }
    @Test
    public void testShowAdjList1() {
        graph.addNode(1, "Push");
        graph.addNode(2, "Pull");

        String adjList = graph.showAdjList(1);
        //Debe estar vacío, ya que no se conectaron los vertices
        Assertions.assertEquals("", adjList);
    }

    @Test
    public void testShowAdjList2() {
        graph3.addNode("Elphant",1);
        graph3.addNode("Lion",3);


        graph3.connectVertex("Elephant", "Elephant", 3);//Loop
        graph3.connectVertex("Elephant", "Elephant", 3);//Loop
        String adjList = graph3.showAdjList("Elephant");

        Assertions.assertEquals("[1][1]", adjList);
    }

    @Test
    public void testShowAdjList3() {
        graph2.addNode(1, 45);
        graph2.addNode(2, 44);
        graph2.addNode(3, 43);

        graph2.connectVertex(1, 2, 2); // Multigraph
        graph2.connectVertex(1, 3, 2); // Multigraph

        String adjList = graph2.showAdjList(1);

        Assertions.assertEquals("[98][99]", adjList);
    }

    @Test
    public void testBfs1() {
        graph.addNode(1, "Jaime");
        graph.bfs(1);

        Vertex<Integer, String> vertex = graph.getHashMapVertexes().get(1); //Obtenemos el vértice a partir de la clave
        Assertions.assertEquals(Color.BLACK, vertex.getColor()); //Una vez ejecutado el Bfs, el color del vértice debe ser BLACK
        Assertions.assertEquals(0, vertex.getDistance()); //Como no tiene padre, su distancia permaneció en 0
        Assertions.assertNull(vertex.getParent()); //Su padre debe ser nulo
    }

    @Test
    public void testBfs2() {
        graph3.addNode("Bart", 3);
        graph3.addNode("Jaime", 6);
        graph3.addNode("Einstein", 8);
        graph3.addNode("Axel", 245);

        graph3.connectVertex("Bart", "Jaime", 1);//Simple Graph
        graph3.connectVertex("Einstein", "Axel", 1);//Simple Graph
        graph3.connectVertex("Bart", "Einstein", 1);//Simple Graph
        graph3.bfs("Bart");

        Vertex<String, Integer> vertex = graph3.getHashMapVertexes().get("Bart");//Obtenemos el vértice a partir de la clave
        Assertions.assertEquals(Color.BLACK, vertex.getColor());
        Assertions.assertNull(vertex.getParent()); //Su padre debe ser nulo

    }

    @Test
    public void testBfs3() {
        graph3.addNode("Bart", 2);
        graph3.addNode("Jaime", 5);
        graph3.addNode("Einstein", 7);
        graph3.addNode("Axel", 111);

        graph3.connectVertex("Bart", "Jaime", 4);//Directed
        graph3.connectVertex("Einstein", "Axel", 4);//Directed
        graph3.connectVertex("Bart", "Einstein", 4);//Directed
        graph3.bfs("Bart"); //Empezamos en el vertice que tiene como clave "Bruno"

        Vertex<String, Integer> vertex = graph3.getHashMapVertexes().get("Axel");//Obtenemos el vértice a partir de la clave
        Assertions.assertEquals(2, vertex.getDistance()); //La distancia de adriana debe ser de 2
        Assertions.assertNotEquals(Color.GRAY, vertex.getColor()); //Al finalizar el BSF todos los vertices siempre van a ser BLACK

    }

    @Test
    public void testDfs1() {
        graph.addNode(1, "Jaime");
        graph.dfs();

        Vertex<Integer, String> vertex = graph.getHashMapVertexes().get(1); //Obtenemos el vértice a partir de la clave
        Assertions.assertEquals(Color.BLACK, vertex.getColor()); //Una vez ejecutado el Dfs, el color de todos los vértices debe ser BLACK
        Assertions.assertEquals(1, vertex.getOriginTime()); //El tiempo de inicio es 1
        Assertions.assertEquals(2, vertex.getEndTime());//El tiempo de final es 2, ya que solo hay un vértice
        Assertions.assertNull(vertex.getParent()); //Su padre debe ser nulo
    }

    @Test
    public void testDfs2() {
        graph3.addNode("Karim", 7);
        graph3.addNode("Alfonso", 5);
        graph3.addNode("Miriam", 24);
        graph3.addNode("Jaime", 84);

        graph3.connectVertex("Karim", "Alfonso", 3);//Directed
        graph3.connectVertex("Karim", "Jaime", 3);//Directed
        graph3.connectVertex("Karim", "Miriam", 3);//Directed
        graph3.dfs();

        //En este caso el Dfs toma como punto de partida al vertice con clave Deicy
        Vertex<String, Integer> vertex = graph3.getHashMapVertexes().get("Miriam");//Obtenemos el vértice a partir de la clave
        Assertions.assertEquals(Color.BLACK, vertex.getColor());
        Assertions.assertEquals(1, vertex.getOriginTime());
        Assertions.assertEquals(2, vertex.getEndTime());
    }

    @Test
    public void testDfs3() {
        graph3.addNode("Karim", 7);
        graph3.addNode("Alex", 5);
        graph3.addNode("Miriam", 24);
        graph3.addNode("Jaime", 84);
        graph3.addNode("Jacob", 33); //Este no está conectado a ningún vertice


        graph3.connectVertex("Karim", "Alfonso", 3);//Directed
        graph3.connectVertex("Karim", "Jaime", 3);//Directed
        graph3.connectVertex("Karim", "Miriam", 3);//Directed
        graph3.dfs();

        Vertex<String, Integer> vertex = graph3.getHashMapVertexes().get("Alex");//Obtenemos el vértice a partir de la clave
        Assertions.assertEquals(1, vertex.getOriginTime()); //El tiempo inicial de Cesar debe ser 6
        Assertions.assertEquals(2, vertex.getEndTime()); //El tiempo final de Cesar debe ser 7
        Assertions.assertNotEquals(Color.GRAY, vertex.getColor()); //Al finalizar el DSF todos los vertices siempre van a ser BLACK

        Vertex<String, Integer> vertex2 = graph3.getHashMapVertexes().get("Jaime");
        Assertions.assertEquals(5, vertex2.getOriginTime()); //El tiempo inicial de Daron debe ser 3
        Assertions.assertEquals(6, vertex2.getEndTime()); //El tiempo final de Daron debe ser 4
        Assertions.assertEquals(Color.BLACK, vertex.getColor());

        Vertex<String, Integer> vertex3 = graph3.getHashMapVertexes().get("Jacob");
        Assertions.assertEquals(Color.BLACK, vertex3.getColor());//Con esto nos aseguramos que efectivamente se alcanzó el vértice que no estaba conectado
    }*/
}
