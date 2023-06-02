package model;

import Exceptions.VertexNotFoundException;

import java.util.*;

public class Graph<T> {

    ArrayList<Vertex<T>> vertices;
    protected HashMap<Integer, Vertex<T>>vertexes;

    private int time = 0;
    private int white = 1;
    private  int grey = 2;
    private int black = 3;

    public Graph() {
        this.vertexes = new HashMap<>();
        this.vertices = new ArrayList<>();
    }

    public void addVertex(T value, int key)throws Exception{
        if(vertexes.containsKey(key)){
            throw new Exception("The node reference with the key " + key + " already exist");
        }else {
            Vertex<T> vertex = new Vertex<>(value, key);
            vertexes.put(key, vertex);
            vertices.add(vertex);
        }
    }

    public void deleteVertex(int key){
        vertexes.remove(key);
    }

    public void BFS(int keyRoot){
        LinkedList<Vertex<T>> queue = new LinkedList<>();
        Vertex<T> vertex;
        Vertex<T> root = vertexes.get(keyRoot);
        int distance = 0;

        for(Map.Entry<Integer, Vertex<T>> c : vertexes.entrySet()){
            c.getValue().setColor(white);
            c.getValue().setDistance(distance);
        }
        root.setDistance(distance);
        root.setColor(grey);
        root.setPadre(null);
        queue.addLast(root);

        while (!queue.isEmpty()){
            vertex = queue.poll();
            for(int i = 0; i<vertex.getAdyacentes().size();i++){
                if(vertex.getAdyacentes().get(i).getColor() == white){
                    vertex.getAdyacentes().get(i).setColor(grey);
                    vertex.getAdyacentes().get(i).setPadre(vertex);
                    vertex.getAdyacentes().get(i).setDistance(vertex.getDistance()+1);
                    queue.addLast(vertex.getAdyacentes().get(i));
                }
            }
            vertex.setColor(black);
        }

    }
    public void DFS(){
        for(Map.Entry<Integer, Vertex<T>> c : vertexes.entrySet()){
            c.getValue().setColor(white);
            c.getValue().setPadre(null);
        }
        for(Map.Entry<Integer, Vertex<T>> c : vertexes.entrySet()){
            if(c.getValue().getColor() == white){
                dfsVisit(c.getValue());
            }
        }
    }
    public void dfsVisit(Vertex<T> vertex){
        time = time + 1;
        vertex.setTime1(time);
        vertex.setPadre(null);
        vertex.setColor(grey);
        for (int i = 0; i<vertex.getAdyacentes().size();i++){
            if (vertex.getAdyacentes().get(i).getColor() == white){
                vertex.getAdyacentes().get(i).setPadre(vertex);
                dfsVisit(vertex.getAdyacentes().get(i));
            }
        }
        vertex.setColor(black);
        time = time +1;
        vertex.setTime2(time);
    }

    public int getHashSize(){
        return vertexes.size();
    }
    public int proveConex(){
        int count = 0;
        for (Map.Entry<Integer, Vertex<T>> c : vertexes.entrySet()){
            BFS(c.getValue().getKey());
            boolean flag = true;
            for(Map.Entry<Integer, Vertex<T>> a : vertexes.entrySet()){
                if(a.getValue().getColor()==white){
                    flag = false;
                }
            }
            if (flag){
                count++;
            }
        }
        return count;
    }

    public void añadirAdyacentes(int vertice, int padre) {

        boolean flag = true;

        int cuenta = 0;

        if(!vertexes.get(padre).getAdyacentes().isEmpty()) {

            for(int l = 0; l < vertexes.get(padre).getAdyacentes().size();l++) {

                if(vertexes.get(padre).getAdyacentes().get(l).getValue() == vertexes.get(vertice).getValue()) {
                    cuenta++;
                }
            }
        }
        if(cuenta >= 1) {

            flag= false;
        }
        if(flag) {

            vertexes.get(vertice).setPadre(vertexes.get(padre));
            vertexes.get(padre).añadirAdyacente(vertexes.get(vertice));
        }
    }
    public void addArista(int keyFrom, int keyTo, int peso) throws Exception{

        if(vertexes.get(keyFrom) != null && vertexes.get(keyTo) != null){

            Edge arista = new Edge(vertexes.get(keyFrom), vertexes.get(keyTo), peso);

            vertexes.get(keyFrom).addEdge(arista);

            añadirAdyacentes(keyTo, keyFrom);
        }else {
            throw new VertexNotFoundException("Invalid vertex input");
        }
    }
    public void initialize() {

        for(Map.Entry<Integer, Vertex<T>> c : vertexes.entrySet()){

            c.getValue().initialize();
        }
    }
    public String dijkstraBase(int source,int to)  {

        List<Vertex<T>> temporalVertices = new ArrayList<Vertex<T>>();

        vertexes.get(source).aristas.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.getWeight()-o2.getWeight();
            }
        });

        vertexes.get(source).setDistance(0);

        temporalVertices.add(vertexes.get(source));

        for(Map.Entry<Integer, Vertex<T>> c : vertexes.entrySet()){
            if(!c.getValue().equals(vertexes.get(source))){
                c.getValue().setDistance(Integer.MAX_VALUE);
                temporalVertices.add(c.getValue());
            }
        }
        temporalVertices.sort((o1, o2) -> o1.getDistance()-o2.getDistance());

        while(!temporalVertices.isEmpty()) {

            Vertex<T> u = temporalVertices.remove(0);

            if(u != null) {

                for(int j = 0; j < u.aristas.size();j++) {

                    int alt = u.getDistance()+u.aristas.get(j).getWeight();

                    if(alt < u.aristas.get(j).getTo().getDistance() && alt>0) {

                        u.aristas.get(j).getTo().setDistance(alt);
                        u.aristas.get(j).getTo().setPadre(u);

                    }
                }
            }
            temporalVertices.sort((o1, o2) -> o1.getDistance()-o2.getDistance());

        }
        ArrayList<Vertex<T>> solution = new ArrayList<>();
        Vertex<T> verte = vertexes.get(to);
        solution.add(verte);
        boolean flag = true;

        for (int i = 0; i<to&&flag;i++){
            if(verte != null) {
                if (verte.getPadre() == vertexes.get(source)) {
                    solution.add(verte.getPadre());
                    flag = false;
                } else {
                    solution.add(verte.getPadre());
                    verte = verte.getPadre();
                    if (verte == null) {
                        flag = false;
                    }
                }
            }
        }

        Collections.reverse(solution);

        String out = "{ ";

        boolean flag2 = true;

        for (int i = 0; i<solution.size()&&flag2;i++){
            if(solution.get(i) != null) {
                if (solution.get(i).getKey() == to) {
                    out += "" + solution.get(i).getValue();
                    flag2 = false;
                } else {
                    out += solution.get(i).getValue() + ", ";
                }
            }
        }
        out += "}";
        int value = 0;
        if(vertexes.get(to) != null) {
             value = vertexes.get(to).getDistance();

            if (value == Integer.MAX_VALUE) {
                return "You can not visit that place from that position ";
            }
        }
        return "Way: " + out + " time of travel :" + value;
    }

    public int dijkstraNumD(int source, int to)  {

        int[] dist2 = new int[vertexes.size()];

        List<Vertex<T>> temporalVertices = new ArrayList<Vertex<T>>();

        vertexes.get(source).aristas.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.getWeight()-o2.getWeight();
            }
        });

        vertexes.get(source).setDistance(0);

        temporalVertices.add(vertexes.get(source));

        for(Map.Entry<Integer, Vertex<T>> c : vertexes.entrySet()){
            if(!c.getValue().equals(vertexes.get(source))){
                c.getValue().setDistance(Integer.MAX_VALUE);
                temporalVertices.add(c.getValue());
            }
        }
        temporalVertices.sort((o1, o2) -> o1.getDistance()-o2.getDistance());

        while(!temporalVertices.isEmpty()) {
            Vertex<T> u = temporalVertices.remove(0);
            if(u != null) {
                for(int j = 0; j < u.aristas.size();j++) {
                    int alt = u.getDistance()+u.aristas.get(j).getWeight();
                    if(alt < u.aristas.get(j).getTo().getDistance()) {
                        u.aristas.get(j).getTo().setDistance(alt);
                        u.aristas.get(j).getTo().setPadre(u);
                    }
                }
            }

            temporalVertices.sort((o1, o2) -> o1.getDistance()-o2.getDistance());

        }

        int value = vertexes.get(to).getDistance();

        return value;

    }

    public boolean prove(int f){
        if(vertexes.get(f).getColor()!= white){
            return true;
        }else{
            return false;
        }

    }

    public HashMap<Integer,Vertex<T>> getVertexes(){
        return vertexes;
    }

    public void deleteArista(int from,int to){
        vertexes.get(from).deleteArista(from,to);
    }
    public boolean proveArista(int from,int to){
        return vertexes.get(from).proveArista(from,to);
    }
    public boolean proveVertex(int v){
        if(vertexes.get(v)!=null){
            return true;
        }
        return false;
    }


}