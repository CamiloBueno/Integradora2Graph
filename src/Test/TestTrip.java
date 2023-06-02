package Test;

import junit.framework.TestCase;
import model.Control;

public class TestTrip extends TestCase {

    private Control control;

    public void setUpStage1() throws Exception {
        control = new Control();
    }

    public void testVertexList() throws Exception {
        setUpStage1();
        control.getGrafo().addVertex("A",0);
        control.getGrafo().addVertex("B",1);
        control.getGrafo().addVertex("C",2);
        control.getGrafo().addVertex("D",3);

        control.getGrafo().addArista(0,2,3);
        control.getGrafo().addArista(0,1,7);
        control.getGrafo().addArista(2,3,8);
        control.getGrafo().addArista(2,1,2);
        control.getGrafo().addArista(1,3,2);

        assertEquals(control.tripFromTo(1,2,3), "Way: { C, B, D} time of travel :4");
    }
    public void testVertexMatrix() throws Exception {
        setUpStage1();
        control.getGrafoM().addVertex("A",0);
        control.getGrafoM().addVertex("B",1);
        control.getGrafoM().addVertex("C",2);
        control.getGrafoM().addVertex("D",3);

        control.getGrafoM().addEdge(0,2,3);
        control.getGrafoM().addEdge(0,1,7);
        control.getGrafoM().addEdge(2,3,8);
        control.getGrafoM().addEdge(2,1,2);
        control.getGrafoM().addEdge(1,3,2);


        assertEquals(control.tripFromTo(2,2,3), "Way : { C, B, D} time of travel : 4");
    }

    public void testFinishTripList() throws Exception {
        setUpStage1();
        control.getGrafo().addVertex("A",0);
        control.getGrafo().addVertex("B",1);
        control.getGrafo().addVertex("C",2);
        control.getGrafo().addVertex("D",3);

        control.getGrafo().addArista(0,2,3);
        control.getGrafo().addArista(0,1,7);
        control.getGrafo().addArista(2,3,8);
        control.getGrafo().addArista(2,1,2);
        control.getGrafo().addArista(1,3,2);

        assertEquals(control.proveFinish(1,0,2),"you can arrive to HOMESTEAD II to create a new life" );
        assertEquals(control.proveFinish(1,1,2),"No, you need to back to another point" );
        assertEquals(control.proveFinish(1,3, 0),"No, you need to back to another point" );
    }
    public void testFinishTripMatrix() throws Exception {
        setUpStage1();
        control.getGrafoM().addVertex("A",0);
        control.getGrafoM().addVertex("B",1);
        control.getGrafoM().addVertex("C",2);
        control.getGrafoM().addVertex("D",3);

        control.getGrafoM().addEdge(0,2,3);
        control.getGrafoM().addEdge(0,1,7);
        control.getGrafoM().addEdge(2,3,8);
        control.getGrafoM().addEdge(2,1,2);
        control.getGrafoM().addEdge(1,3,2);

        assertEquals(control.proveFinish(2,0,2),"you can arrive to HOMESTEAD II to create a new life" );
        assertEquals(control.proveFinish(2,1,2),"No, you need to back to another point" );
        assertEquals(control.proveFinish(2,3, 0),"No, you need to back to another point" );
    }

}
