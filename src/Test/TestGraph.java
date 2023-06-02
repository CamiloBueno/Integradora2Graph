package Test;

import junit.framework.TestCase;
import model.Control;
import model.Vertex;

public class TestGraph extends TestCase {

    public Control control;

    public void setUpStage1() throws Exception {
        control = new Control();
    }

    public void testAdd() throws Exception {
        setUpStage1();
        Vertex<String> vertex = new Vertex<>("A",0);
        control.getGrafo().getVertexes().put(0,vertex);
        control.getGrafo().addVertex("B",1);
        control.getGrafo().addVertex("C",2);
        control.getGrafo().addVertex("D",3);

        control.getGrafo().addArista(0,2,3);
        control.getGrafo().addArista(0,1,7);
        control.getGrafo().addArista(2,3,8);
        control.getGrafo().addArista(2,1,2);
        control.getGrafo().addArista(1,3,2);

        assertEquals(control.getGrafo().getVertexes().get(0),vertex);
        Vertex verte = control.getGrafo().getVertexes().get(3);
        assertEquals(verte.getValue(),"D");
        assertEquals((verte.getKey()),3);
        assertEquals(control.getGrafo().proveArista(1,3),true);
    }

    public void testDelete() throws Exception {
        setUpStage1();

        Vertex<String> vertex = new Vertex<>("A",0);
        control.getGrafo().getVertexes().put(0,vertex);
        control.getGrafo().addVertex("B",1);
        control.getGrafo().addVertex("C",2);
        control.getGrafo().addVertex("D",3);

        control.getGrafo().addArista(0,2,3);
        control.getGrafo().addArista(0,1,7);
        control.getGrafo().addArista(2,3,8);
        control.getGrafo().addArista(2,1,2);
        control.getGrafo().addArista(1,3,2);

        control.getGrafo().deleteArista(0,2);
        control.getGrafo().deleteVertex(0);
        assertFalse(control.getGrafo().getVertexes().get(1).proveArista(0,2));
        assertEquals(control.getGrafo().proveVertex(0),false);

    }

}
