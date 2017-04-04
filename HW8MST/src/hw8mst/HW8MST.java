package hw8mst;

/**
 * CSCI 333, Homework 8: Prim's Minimum Spanning Tree Algorithm. Used to test
 * the Graph class. Ensures the methods of the Graph class all work to
 * specifications, such as the PrimMST() method performing the correct
 * operation.
 *
 * @author Ryan Fairbanks
 * @version 11/17/16
 */
public class HW8MST {

    /**
     * Used to test the Graph, Edge, and Vertex classes. Creates a Graph object
     * via the use of a 2D array of Edge objects, each with a boolean value
     * indicating that they exist and a weight in between the Vertex objects
     * represented by the position. Ensures that the primMST() and printGraph()
     * methods in the Graph class work correctly. Prints all of the Vertex
     * objects in the Graph after all other method tests have been completed.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        Edge[][] testEdges = new Edge[][]{
            {new Edge(), new Edge(true, 9), new Edge(true, 6), new Edge(true, 4), new Edge()},
            {new Edge(true, 9), new Edge(), new Edge(true, 1), new Edge(), new Edge()},
            {new Edge(true, 6), new Edge(true, 1), new Edge(), new Edge(true, 2), new Edge()},
            {new Edge(true, 4), new Edge(), new Edge(true, 2), new Edge(), new Edge(true, 3)},
            {new Edge(), new Edge(), new Edge(), new Edge(true, 3), new Edge()}
        };
        Graph testGraph = new Graph(testEdges);
        testGraph.primMST(); // Perform Prim's Minimum Search Tree algorithm.
        testGraph.printGraph(); // Print the graph, the 2D array of Edge objects.
        System.out.println();
        for (int i = 0; i < testGraph.getN(); i++) { // Print all Vertex objects.
            System.out.println(testGraph.getVertex(i).toString());
        }
    }
}
