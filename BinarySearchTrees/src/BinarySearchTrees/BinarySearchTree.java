package BinarySearchTrees;

/**
 * CSCI 333, Homework 6: Binary Search Trees. This class implements the methods
 * used to modify and otherwise access the binary search tree including
 * insertion, deletion, transversals, and other operations.
 *
 * @author Ryan Fairbanks
 * @version 10/19/16
 */
public class BinarySearchTree {

    private BSTNode root; // Root of the binary search tree.
    private int size; // Number of elements in the binary search tree.

    /**
     * Constructor for the binary search tree, sets it to be empty at first by
     * setting the root to null and the size to 0.
     */
    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Searches the binary search tree for the passed key, returns node where
     * the key was found, returns null if the key was not found.
     *
     * @param node Current position in the binary search tree.
     * @param searchKey Key being searched for in the binary search tree.
     * @return Node containing the key being searched for.
     */
    public BSTNode search(BSTNode node, int searchKey) {
        if (node == null) {
            return null; // Empty subtree.
        }
        if (searchKey == node.getKey()) {
            return node; // Key is at subtree root.
        }
        if (searchKey < node.getKey()) {
            return search(node.getLeft(), searchKey); // Recurse to left subtree.
        } else {
            return search(node.getRight(), searchKey); // Recurse to right subtree.
        }
    }

    /**
     * Transverses down the left side of the subtree until the bottom node is
     * reached. The node at this position holds the smallest key and is
     * returned.
     *
     * @param node Starting position in the binary search tree.
     * @return The node containing the smallest key within the passed binary
     * search tree.
     */
    public BSTNode minimum(BSTNode node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    /**
     * Transverses down the right side of the subtree until the bottom node is
     * reached. The node at this position hold the largest key and is returned.
     *
     * @param node Starting position in the binary search tree.
     * @return The node containing the largest key within the passed binary
     * search tree.
     */
    public BSTNode maximum(BSTNode node) {
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }

    /**
     * Finds the node with the next largest key in respect to the node passed,
     * otherwise known as a node's y.
     *
     * @param node The node to find a y for.
     * @return The node with the next largest key.
     */
    public BSTNode successor(BSTNode node) {
        if (node.getRight() != null) {
            return minimum(node.getRight()); // Minimum of right subtree.
        }
        while (node.getP() != null & node == node.getP().getRight()) {
            node = node.getP(); // Find the left parent, go up to parent for next iteration.
        }
        return node.getP();
    }

    /**
     * Finds the node with the next smallest key in respect to the node passed,
     * otherwise known as the node's predecessor.
     *
     * @param node The node to find a predecessor for.
     * @return The node with the next smallest key.
     */
    public BSTNode predecessor(BSTNode node) {
        if (node.getLeft() != null) {
            return maximum(node.getLeft()); // Maximum of left subtree.
        }
        while (node.getP() != null & node == node.getP().getLeft()) {
            node = node.getP(); // Find right parent, go up to parent for next iteration.
        }
        return node.getP();
    }

    /**
     * Inserts a new node into the binary search tree.
     *
     * @param newNode The new node to be inserted into the binary search tree.
     */
    public void insert(BSTNode newNode) {
        BSTNode currentParent = null; // A trailing pointer to the parent of the current node.
        BSTNode node = this.root; // Set node to the root of the binary search tree.
        while (node != null) { // Move down the tree one level per iteration.
            currentParent = node;
            if (newNode.getKey() < node.getKey()) {
                node = node.getLeft(); // Binary search tree property is indicator to go left.
            } else {
                node = node.getRight(); // Binary search tree property indicates to go right.
            }
        }
        newNode.setP(currentParent); // Loop is done. Location found. Set parent handle of newNode.
        if (currentParent == null) {
            this.root = newNode; // If tree was an empty binary search tree, newNode is the new root of the tree.
        } else if (newNode.getKey() < currentParent.getKey()) {
            currentParent.setLeft(newNode); // newNode's parent is currentParent, so set child handle to newNode. newNode is a left child.
        } else {
            currentParent.setRight(newNode); // newNode is a right child.
        }
        this.size++; // Increment size due to added element.
    }

    /**
     * Deletes x in the passed binary search tree and moves other elements to
     * compensate for its deletion.
     *
     * @param tree Tree in which the deletion is to occur.
     * @param x Node to be deleted in the passed tree.
     */
    public void delete(BSTNode tree, BSTNode x) {
        BSTNode y;
        if (x.getLeft() == null) {
            transplant(tree, x, x.getRight()); // Case 1: x's left child is null.
        } else if (x.getRight() == null) {
            transplant(tree, x, x.getLeft()); // Case 2: x's right child is null.
        } else { // x has both children.
            y = minimum(x.getRight()); // Find x's y.
            if (y.getP() != x) { // Case 3b:x's y is not its right child.
                transplant(tree, y, y.getRight());
                y.setRight(x.getRight());
                y.getRight().setP(y);
            }
            transplant(tree, x, y); // Case 3a, which is also step 2 of case 3b.
            y.setLeft(x.getLeft());
            y.getLeft().setP(y);
        }
        this.size--; // Decrement size due to deleted element.
    }

    /**
     * Helper method that replaces the subtree rooted at deleteNode with the
     * subtree rooted at replaceNode in the binary search tree (passed as tree).
     *
     * @param tree The binary search tree or portion of the binary search tree
     * that is affected.
     * @param deleteNode Root of subtree to be replaced.
     * @param replaceNode Root of subtree to replaceNode deleteNode's subtree.
     */
    private void transplant(BSTNode tree, BSTNode deleteNode, BSTNode replaceNode) {
        if (deleteNode.getP() == null) {
            this.root = replaceNode; // If deleteNode was the old root, then replaceNode is the new root.
        } else if (deleteNode == deleteNode.getP().getLeft()) {
            deleteNode.getP().setLeft(replaceNode); // If deleteNode was the left child of deleteNode's parent, then replaceNode is the new left child of deleteNode's parent.
        } else {
            deleteNode.getP().setRight(replaceNode); // deleteNode was right child of the parent.
        }
        if (replaceNode != null) {
            replaceNode.setP(deleteNode.getP()); // Set replaceNode's parent to p, the old parent of deleteNode.
        }
    }

    /**
     * Transverses the binary search tree by visiting a node then visits the
     * children of that node. Tail recursive.
     *
     * @param node Current position in the binary search tree.
     */
    public void preOrder(BSTNode node) {
        if (node != null) { // Base case is the implied empty else.
            System.out.print(node.getKey() + " "); // Print the node's key
            preOrder(node.getLeft()); // Recurse on left subtree.
            preOrder(node.getRight()); // Recurse on right subtree.
        }
    }

    /**
     * Transverses the binary search tree by visiting the left child of the
     * node, then the parent (itself), and lastly the right child of the node.
     *
     * @param node Current position in the binary search tree.
     */
    public void inOrder(BSTNode node) {
        if (node != null) {
            inOrder(node.getLeft());
            System.out.print(node.getKey() + " ");
            inOrder(node.getRight());
        }
    }

    /**
     * Transverses the binary search tree by visiting a node after visiting the
     * node's left child and right child. Head recursive.
     *
     * @param node Current position in the binary search tree.
     */
    public void postOrder(BSTNode node) {
        if (node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.print(node.getKey() + " ");
        }
    }

    /**
     * Get the number of elements in the binary search tree.
     *
     * @return Size of the binary search tree.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Get the root of the binary search tree for use in testing via the main
     * class.
     *
     * @return Root of the binary search tree.
     */
    public BSTNode getRoot() {
        return this.root;
    }
}
