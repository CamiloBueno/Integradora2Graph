package model;

import Exceptions.VertexNotFoundException;

import java.util.*;


public class GraphM<T> {

    private ArrayList<Vertex<T>> vertices;

    private HashMap<Integer,Vertex<T>> vertexes;

    private ArrayList<Edge>[][] matriz;

    private int time = 0;
    private int white = 1;
    private  int grey = 2;
    private int black = 3;


    public GraphM() {
        this.vertices = new ArrayList<>();
        this.matriz = new ArrayList [48][48];
        this.vertexes = new HashMap<>();
        for(int i = 0; i<48;i++){
            for (int j = 0;j<48;j++){
                matriz[i][j] = new ArrayList<>();
            }
        }

    }

    public void addEdge(int v1, int v2 , int value){
        if(vertexes.get(v1) != null && vertexes.get(v2) != null){
            Edge ed = new Edge<>(vertexes.get(v1),vertexes.get(v2),value);
            matriz [v1] [v2].add(ed);
        }else {
            throw new VertexNotFoundException("The vertex does not exist.");
        }
    }

    public void addVertex(T value, int num){
        Vertex<T> v = new Vertex<>(value,num);
        vertexes.put(num,v);
    }

    public void BFS(int keyRoot){
        LinkedList<Vertex<T>> queue = new LinkedList<>();
        Vertex<T> vertex = new Vertex<>(null,-1);
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
            int ind = vertex.getKey();
            for (int i = 0; i<48;i++){
                if(!matriz[ind] [i].isEmpty()){
                    if (matriz[ind][i].get(0)!=null){

                        if(matriz[ind][i].get(0).getTo().getColor()== white) {

                            matriz[ind][i].get(0).getTo().setColor(grey);
                            matriz[ind][i].get(0).getTo().setPadre(vertex);
                            matriz[ind][i].get(0).getTo().setDistance(vertexes.get(ind).getDistance() + 1);
                            queue.addLast(matriz[ind][i].get(0).getTo());
                        }
                    }
                }
            }
            vertex.setColor(black);
        }

    }

    public String dijkstraBase(int v,int to){
        ArrayList<Vertex<T>> solution = new ArrayList<>();
        Vertex<T> source = vertexes.get(v);
        source.setDistance(0);

        ArrayList<Vertex<T>> Q = new ArrayList<>();
        Q.add(source);

        for(Map.Entry<Integer,Vertex<T>> c : vertexes.entrySet()){
            if(!c.getValue().equals(source)){
                c.getValue().setDistance(Integer.MAX_VALUE);
                Q.add(c.getValue());
            }
        }
        Q.sort(new Comparator<Vertex<T>>() {
            @Override
            public int compare(Vertex<T> o1, Vertex<T> o2) {
                return o1.getDistance()-o2.getDistance();
            }
        });

        while (!Q.isEmpty()){

            Vertex<T> u = Q.remove(0);

            for (int i = 0; i<48;i++){
                if(matriz[u.getKey()][i]!=null){
                    int minus = Integer.MAX_VALUE;
                    for (int j = 0; j<matriz[u.getKey()][i].size();j++){

                        int alt = u.getDistance() + matriz[u.getKey()][i].get(j).getWeight();
                        if(minus>alt){
                            minus = alt;
                        }
                        if(minus<vertexes.get(i).getDistance() && minus>0){
                            vertexes.get(i).setDistance(minus);
                            vertexes.get(i).setPadre(u);
                            Q.sort(new Comparator<Vertex<T>>() {
                                @Override
                                public int compare(Vertex<T> o1, Vertex<T> o2) {
                                    return o1.getDistance()-o2.getDistance();
                                }
                            });
                        }
                    }
                }
            }
        }
        boolean flag2 = true;
        Vertex<T> verte = vertexes.get(to);
        solution.add(verte);
        for(int i=0;i<to&&flag2;i++){
            if(verte != null) {
                if (verte.getPadre() == vertexes.get(v)) {
                    solution.add(verte.getPadre());
                    flag2 = false;
                } else {
                    solution.add(verte.getPadre());
                    verte = verte.getPadre();
                    if (verte == null) {
                        flag2 = false;
                    }
                }
            }
        }
        Collections.reverse(solution);
        String out = "{ ";
        boolean flag = true;
        for (int i = 0; i<solution.size()&&flag;i++){
            if(solution.get(i)!= null) {
                if (solution.get(i).getKey() == to) {
                    out += "" + solution.get(i).getValue();
                    flag = false;
                } else {
                    out += solution.get(i).getValue() + ", ";
                }
            }
        }
        out += "}";
        int value = vertexes.get(to).getDistance();
        if(value == Integer.MAX_VALUE){
            return "You can not visit that place starting in your actual position (vertex) ";
        }
        return "Way : " + out + " time of travel : " + value;

    }
    public int dijkstraNumD(int v,int to){
        ArrayList<Vertex<T>> solution = new ArrayList<>();
        Vertex<T> source = vertexes.get(v);
        source.setDistance(v);

        ArrayList<Vertex<T>> Q = new ArrayList<>();
        Q.add(source);

        for(Map.Entry<Integer,Vertex<T>> c : vertexes.entrySet()){
            if(!c.getValue().equals(source)){
                c.getValue().setDistance(Integer.MAX_VALUE);
                Q.add(c.getValue());
            }
        }
        Q.sort(new Comparator<Vertex<T>>() {
            @Override
            public int compare(Vertex<T> o1, Vertex<T> o2) {
                return o1.getDistance()-o2.getDistance();
            }
        });
        while (!Q.isEmpty()){
            Vertex<T> u = Q.remove(0);
            for (int i = 0; i<48;i++){
                if(matriz[u.getKey()][i]!=null){
                    int minus = Integer.MAX_VALUE;
                    for (int j = 0; j<matriz[u.getKey()][i].size();j++){

                        int alt = u.getDistance() + matriz[u.getKey()][i].get(j).getWeight();
                        if(minus>alt){
                            minus = alt;
                        }
                        if(minus<vertexes.get(i).getDistance()){
                            vertexes.get(i).setDistance(minus);
                            vertexes.get(i).setPadre(u);
                            Q.sort(new Comparator<Vertex<T>>() {
                                @Override
                                public int compare(Vertex<T> o1, Vertex<T> o2) {
                                    return o1.getDistance()-o2.getDistance();
                                }
                            });
                        }
                    }

                }
            }
        }
        int value = vertexes.get(to).getDistance();

        return value;
    }
    public boolean prove(int f){
        if(vertexes.get(f).getColor()!= white ){
            return true;
        }else{
            return false;
        }
    }

}
