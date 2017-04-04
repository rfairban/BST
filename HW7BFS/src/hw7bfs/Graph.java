package hw7bfs;

import java.util.LinkedList;

/**
 * CSCI 333, Homework 7:Breadth-First Search. Features a constructor to create a
 * graph, a breadth first search method to transverse the graph, and a method to
 * print the graph.
 *
 * @author Ryan Fairbanks
 * @version 11/2/16
 */
public class Graph {

    private int n; // Number of vertices in the graph.
    private boolean[][] edges; // Used to store an adjaceny matrix.
    private Vertex[] vertices; // Used to store information about each vertex.

    /**
     * Constructor for Graph. Uses the length of passed boolean 2D array to
     * initialize n, which in turn is used to initialize both lengths of edge
     * for this class instance. With the passed 2D array and edges being of the
     * same size, the data is copied from the passed 2D array into edges.
     * Lastly, vertices is initialized using the recently set n value.
     *
     * @param square 2D boolean array that is square, having the same length in
     * both dimensions. The length of square is used to initialize all of the
     * instance variables and the data within square is directly copied into
     * edges.
     */
    public Graph(boolean[][] square) {
        this.n = square.length;
        this.edges = new boolean[this.n][this.n];
        for (int x = 0; x < square.length; x++) {
            for (int y = 0; y < square[x].length; y++) {
                this.edges[x][y] = square[x][y];
            }
        }
        this.vertices = new Vertex[this.n];
        for (int i = 0; i < this.vertices.length; i++) {
            this.vertices[i] = new Vertex(i);
        }
    }

    /**
     * Get the value for n for the instance of the class.
     *
     * @return The number of vertices in the graph.
     */
    public int getN() {
        return this.n;
    }

    /**
     * Get the value for the Vertex at the passed index.
     *
     * @param index Indicates the Vertex label to return.
     * @return The Vertex which is held at the location represented by the
     * passed index.
     */
    public Vertex getVertex(int index) {
        return this.vertices[index];
    }

    /**
     * Prints the number of vertices in the graph and then prints a table of the
     * adjacency matrix.
     */
    public void printGraph() {
        System.out.println("Number of verticies in the graph: " + this.n);
        for (int x = 0; x < this.edges.length; x++) {
            for (int y = 0; y < this.edges[x].length; y++) {
                System.out.print(this.edges[x][y] + "\t");
            }
            System.out.println(); // Next row.
        }
    }

    /**
     * Explores all vertices of a single connected component of a graph,
     * starting at the provided source vertex, whose index is represented by the
     * passed integer, sourceLabel. Numbers are used to represent the color of a
     * Vertex: 0 is white, 1 is gray, and 2 is black.
     *
     * @param sourceLabel Index of the source vertex in the array of vertices.
     * @throws Exception Color parameter used in Vertex is not valid. This means
     * that the color of the Vertex was set to another value besides white,
     * gray, or black.
     */
    public void breadthFirstSearch(int sourceLabel) throws Exception {
        Vertex s = this.vertices[sourceLabel]; // Vertex with matching sourceLabel.
        LinkedList<Vertex> Q = new LinkedList(); // New empty Q.
        s.setColor(1); // Set s to gray.
        s.setDistance(0); // s is distance 0 from itself.
        s.setParent(null); // s is the root of the breadth first search tree.
        Q.offer(s); // Put Vertex s into the queue: the first vertex to be visited.
        while (Q.isEmpty() != true) {
            Vertex u = Q.poll(); // Remove a Vertex u to visit, from Q.
            u.setColor(2); // Set u to black.
            for (int i = 0; i < this.vertices.length; i++) {
                if (this.edges[u.getDistance()][i] == true) {
                    Vertex v = this.vertices[i];
                    if (v.getColor() == 0) { // Gray or black neighbors are left alone, find white neighbors.
                        v.setColor(1); // Set v to gray.
                        v.setDistance(u.getDistance() + 1); // Vertex v (the child of u) is 1 further from s than u.
                        v.setParent(u); // Vertex v is added to the breadth first search tree as a child of Vertex u.
                        Q.offer(v); // Put Vertex v in the same queue of the vertices to visit later.
                    }
                }
            }
        }
    }
}
