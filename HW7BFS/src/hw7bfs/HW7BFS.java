package hw7bfs;

/**
 * CSCI 333, Homework 7:Breadth-First Search. Main class to test the methods in
 * graph and ensure that the breadth first search is working properly.
 *
 * @author Ryan Fairbanks
 * @version 11/2/16
 */
public class HW7BFS {

    /**
     * Takes a 2D boolean array, calls the constructor from graph, calls
     * breadthFirstSearch, and prints the results. Tests the Graph class.
     *
     * @param edges 2D boolean array containing values indicating adjacent
     * values used to generate the graph.
     * @throws Exception Color parameter used in Vertex is not valid. This means
     * that the color of the Vertex was set to another value besides white,
     * gray, or black.
     */
    public static void graphIt(boolean[][] edges) throws Exception {
        Graph graph = new Graph(edges);
        graph.breadthFirstSearch(0);
        graph.printGraph();
        System.out.println("\nPrinting vertices: ");
        for (int i = 0; i < graph.getN(); i++) {
            System.out.println(graph.getVertex(i).toString());
        }
    }
    /**
     * 
     * Used for testing the Graph class. Creates three 2D boolean arrays and 
     * calls the graphIt method to run the tests.
     * 
     * @param args
     * @throws Exception Color parameter used in Vertex is not valid. This means
     * that the color of the Vertex was set to another value besides white, 
     * gray, or black.
     */
    public static void main(String[] args) throws Exception {
        boolean[][] edges1 = new boolean[][]{ // Graph from class example, not including 6, 7, and 8.
            {false, true, true, true, false, false},
            {false, false, false, false, false, false},
            {false, false, false, true, true, false},
            {false, false, false, false, false, true},
            {false, true, false, false, false, false},
            {false, false, true, false, false, false}
        };
        boolean[][] edges2 = new boolean[][]{
            {false, false, true, false, false},
            {true, false, false, true, false},
            {false, true, false, false, false},
            {false, false, false, false, false},
            {false, false, true, true, false}
        };
        boolean[][] edges3 = new boolean[][]{
            {false, true, false, false, false},
            {false, false, false, true, false},
            {true, false, false, true, false},
            {false, false, false, false, true},
            {false, false, true, false, false}
        };
        System.out.println("First graph: ");
        graphIt(edges1);
        System.out.println("\nSecond graph: ");
        graphIt(edges2);
        System.out.println("\nThird graph: ");
        graphIt(edges3);
    }
}
