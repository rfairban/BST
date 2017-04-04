package BinarySearchTrees;

/**
 * CSCI 333, Homework 6: Binary Search Trees. Implementation of individual nodes
 * of the binary search tree with BSTNodes inside of it relating it to its
 * parent, its left child, and its right child as well as an integer for storing
 * it's key value.
 *
 * @author Ryan Fairbanks
 * @version 10/19/16
 */
public class BSTNode {

    private BSTNode p;
    private BSTNode left;
    private BSTNode right;
    private final int key;

    /**
     * Constructor for nodes.
     *
     * @param key Value to be stored in the node.
     */
    public BSTNode(int key) {
        this.p = null; // Node's parent.
        this.left = null; // Node's left child.
        this.right = null; // Node's right child.
        this.key = key; // Node's stored value.
    }

    /**
     * Getter for p.
     *
     * @return Node's parent.
     */
    public BSTNode getP() {
        return this.p;
    }

    /**
     * Getter for left.
     *
     * @return Node's left child.
     */
    public BSTNode getLeft() {
        return this.left;
    }

    /**
     * Getter for right.
     *
     * @return Node's right child.
     */
    public BSTNode getRight() {
        return this.right;
    }

    /**
     * Getter for key.
     *
     * @return Node's stored value, key.
     */
    public int getKey() {
        return this.key;
    }

    /**
     * Setter for node's parent, p.
     *
     * @param p New parent node.
     * @return Previous parent node.
     */
    public BSTNode setP(BSTNode p) {
        BSTNode tempP = this.p;
        this.p = p;
        return tempP;
    }

    /**
     * Setter for node's left child, left.
     *
     * @param left New left child node.
     * @return Previous left child node.
     */
    public BSTNode setLeft(BSTNode left) {
        BSTNode tempLeft = this.left;
        this.left = left;
        return tempLeft;
    }

    /**
     * Setter for node's right child, right.
     *
     * @param right New right child node.
     * @return Previous right child node.
     */
    public BSTNode setRight(BSTNode right) {
        BSTNode tempRight = this.right;
        this.right = right;
        return tempRight;
    }

}
