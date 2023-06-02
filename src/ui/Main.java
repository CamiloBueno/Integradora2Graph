package ui;
import model.Control;

import java.util.Scanner;

public class Main {
    private Control control;
    private Scanner sc;

    public Main() throws Exception {
        control = new Control();
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) throws Exception {
        Main domi = new Main();
        System.out.println("Import the graph files(First the graph vertexes.txt and then the graph edges.txt).");
        domi.importar();
        int option = -1;
        int graph = 0;
        do {
            option = domi.showMenu();
            domi.operation(option);
        } while (option != 0);
    }

    public int showMenu() {
        System.out.println("Menu: \n" +
                "(1) Exit \n" +
                "(2) \n" + "Display the fastest route to reach the habitable planet. \n" +
                "(3) \n" + "Check if it is possible to reach the habitable planet from a specific point.");

        int option = sc.nextInt();
        sc.nextLine();
        return option;
    }
    public int selecGraph(){
        System.out.println("Select the type of graph implementation for solve the problem: \n " +
                "(1) Adjacency list \n" +
                "(2) Adjacency matrix");

        int option = sc.nextInt();
        sc.nextLine();
        return option;
    }
    public void operation(int option) throws Exception {
        switch (option) {

            case 1:

                System.exit(0);
                break;
            case 2:
                int graph = selecGraph();
                dijks(graph);
                break;
            case 3:
                graph = selecGraph();
                System.out.println("Input the key from the point that you are located:");
                int point =  sc.nextInt();
                sc.nextLine();
                System.out.println(control.proveFinish(graph,point,47));
                break;
            default:
                System.out.println("Invalid input");

        }
    }
    public void dijks(int graph){

        System.out.println("Select a case : \n" +
                "(1) Less Trip from the Earth to Habitable Planet HOMESTEAD II) \n" +
                "(2) Show the fastest route from a point that you select to another (not upper that the point of star)\n");
        int i = sc.nextInt();
        sc.nextLine();

        switch(i){
            case 1:
                System.out.println(control.TripFEarth(graph));
                break;
            case 2:
                System.out.println("Your destination key can not be upper than your start point key");
                System.out.println("Input the key from where you start your Trip:");
                int start = sc.nextInt();
                sc.nextLine();
                System.out.println("Input the key where you finish the Trip:");
                int to = sc.nextInt();
                sc.nextLine();
                System.out.println(control.tripFromTo(graph, start, to));
        }
    }
    public void importar() throws Exception{
        control.loadVertexDataBase();
        control.loadEdgeDataBase();
    }
}




