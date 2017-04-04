package hw8mst;

import java.util.PriorityQueue;

/**
 * CSCI 333, Homework 8: Prim's Minimum Spanning Tree Algorithm. Used to
 * construct objects containing Vertex objects with their relative Edge objects.
 * Methods are also used to print the created Graph and test the Prim Minimum
 * Search Tree.
 *
 * @author awhitley and Ryan Fairbanks
 * @version 11/17/16
 */
public class Graph {

    // ----- Data Fields -----
    private Vertex[] vertices; // An array of Vertex objects to be initialized wtih size n and to store information about each Vertex.
    private int n; // Number of vertices in graph.
    private Edge[][] edges; // 2D array of Edge to initialized with size n by n and store an adjaceny matrix of Edge objects.

    // ----- Constructor -----
    public Graph(Edge[][] newEdges) {
        this.n = newEdges.length;
        this.edges = new Edge[this.n][this.n];
        for (int x = 0; x < this.n; x++) {
            for (int y = 0; y < this.n; y++) {
                this.edges[x][y] = newEdges[x][y];
            }
        }
        this.vertices = new Vertex[this.n];
        for (int label = 0; label < this.n; label++) {
            this.vertices[label] = new Vertex(label, Edge.INFINITE_WEIGHT); // Using -1 as infinity.
        }
    }

    // ----- Getter and Setter Methods -----
    /**
     * Retrieves the n value of the calling object.
     *
     * @return n value of passed object.
     */
    public int getN() {
        return this.n;
    }

    /**
     * Retrieves a Vertex from the calling object's vertices based on the passed
     * index.
     *
     * @param i The position of the Vertex in the array to be retrieved.
     * @return The Vertex object from the passed index.
     */
    public Vertex getVertex(int i) {
        return this.vertices[i];
    }

    /**
     * Retrieves an Edge from the calling object's edges 2D array by using the
     * passed x and y variables as indices for the array.
     *
     * @param x First index.
     * @param y Second index.
     * @return The edge value at the given position.
     */
    public Edge getEdge(int x, int y) {
        return this.edges[x][y];
    }

    // ----- Private Helper Methods -----
    /**
     * Tells you whether a Vertex with the provided label is still in the queue
     * q. This is needed because, since the PriorityQueue is keyed on key not
     * label, it will tell you whether a given key is still in it, but not
     * whether a given label is still in it.
     *
     * @param q a PriorityQueue<Vertex>
     * @param label The Vertex label to check for.
     * @return Returns true if a Vertex with matching label is in q. Returns
     * false if no Vertex with matching label is in q.
     */
    private boolean isStillInQ(PriorityQueue<Vertex> q, int label) {

        Vertex[] array = q.toArray(new Vertex[0]); // dump out an array of the elements

        // traverse the array of elements, searching for a matching label
        for (int i = 0; i < array.length; i++) {
            if ((array[i]).getLabel() == label) {
                return true;
            }
        }

        return false; // no matching label found

    }

    /**
     * Takes the Vertex with matching label in queue q, and reduces its key to
     * newKey. Will return false if Vertex is not in the queue, or newKey is
     * larger than old key. Will return true if it successfully reduced the key.
     *
     * @param q The priority queue of Vertex
     * @param label The label of the Vertex whose key you want to decrease
     * @param newKey
     * @return Returns false if the Vertex with the given label is not in the
     * queue. Returns false if the newKey is larger than the old key of Vertex
     * with given label. Returns true otherwise; the vertex with the given label
     * had its key changed to newKey.
     */
    private boolean decreaseKey(PriorityQueue<Vertex> q, int label, int newKey) {
        // PAY NO ATTENTION TO THE CODE BEHIND THAT CURTAIN! ;)
        // Don't worry about the code in this method body. Read the Javadoc above.

        int indexOfVertex = -1;
        Vertex[] array = q.toArray(new Vertex[0]);

        // check to see if Vertex with the given label is in the Priority queue.
        for (int i = 0; i < array.length; i++) {
            if ((array[i]).getLabel() == label) {
                indexOfVertex = i;
            }
        }

        // if Vertex with the given label is not in the queue, do nothing and return false
        // also returns false if the new key is larger than the old key.
        if (indexOfVertex == -1 || newKey > array[indexOfVertex].getKey()) {
            return false;
        }

        // Without decreaseKey already in the PriorityQueue class,
        // I must remove the vertex and add it again with a different key. 
        // Actually, I'm emptying the queue, then I am inserting all the other
        // vertices back in, except the one being decreased. Then I am reinserting
        // the decreased vertex, with the newKey key value.
        // I had to resort to this because technically you can't remove 
        // an element from a PriorityQueue by its label, since it is keyed on something else.
        Vertex vertexToDecrease = array[indexOfVertex];
        vertexToDecrease.setKey(newKey);

        // clear and rebuild the priority queue
        q.clear();
        for (int i = 0; i < array.length; i++) { // add everything else
            if (i != indexOfVertex) { // not including the old vertex to be reduced
                q.add(array[i]);
            }
        }
        q.add(vertexToDecrease); // insert the decreased vertex back in

        return true; // queue is now effectively identical to before, but with one Vertex's key reduced to newKey
    }

    /**
     * Prints the number of vertices in the graph followed by the square 2D
     * array of Edge objects used to generate the graph.
     */
    public void printGraph() {
        System.out.println("Number of vertices in the graph: " + this.n);
        for (int x = 0; x < this.edges.length; x++) {
            for (int y = 0; y < this.edges[x].length; y++) {
                System.out.print(this.edges[x][y].toString() + "\t");
            }
            System.out.println(); // Next row.
        }
    }

    /**
     * Completes a Prim's Minimum Spanning Tree operation. Uses a priority queue
     * to find the spanning tree with the least total Edge weight between Vertex
     * objects.
     */
    public void primMST() {
        int source = 0; // The location of the source Vertex in the vertices array.
        this.vertices[source].setKey(0);
        PriorityQueue<Vertex> Q = new PriorityQueue(); // New empty Q.
        for (int i = 0; i < this.vertices.length; i++) { // Load vertices entirely into Q.
            Q.add(this.vertices[i]);
        }
        while (Q.isEmpty() != true) {
            Vertex u = Q.poll(); // EXTRACT-MIN
            for (int i = 0; i < this.vertices.length; i++) { // Transverse vertices array at Vertex u looking for matches.
                if (this.edges[u.getLabel()][i].getExists() == true && isStillInQ(Q, this.vertices[i].getLabel()) == true && this.edges[u.getLabel()][i].getWeight() < u.getKey()) {
                    this.vertices[i].setParent(u); // Set parent of vertices[i], Vertex v in the pseudocode, to be Vertex u.
                    this.decreaseKey(Q, i, this.edges[u.getLabel()][i].getWeight()); // Decrease the key of Vertex v.
                }
            }
        }
    }
}
