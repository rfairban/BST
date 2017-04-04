package BinarySearchTrees;

/**
 * CSCI 333, Homework 6: Binary Search Trees. This class is used to test the
 * BSTNode and BinarySearchTree classes by testing the methods of
 * BinarySearchTree. Also features a method known as bstSort to sort arrays by
 * inserting all of their values into a binary search tree and print the result
 * by using an in-order transversal.
 *
 * @author Ryan Fairbanks
 * @version 10/19/16
 */
public class Main {

    private static BinarySearchTree tree;

    /**
     * Sorts the values in the passed array by inserting them into a binary
     * search tree. After the values are inserted, the method inOrder() is
     * called to transverse the tree and print the transversal.
     *
     * @param array Contains integer values to be sorted and printed.
     */
    public static void bstSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            tree.insert(new BSTNode(array[i]));
        }
        tree.inOrder(tree.getRoot());
    }

    /**
     * Uses the passed searchKey in a search to the binary search tree. Prints
     * the results of the search, whether the searchKey was found or not.
     *
     * @param searchKey Value being searched for.
     * @return Node in the binary search tree containing the searchKey or null
     * if the searchKey was not found.
     */
    public static BSTNode searchTree(int searchKey) {
        BSTNode found = tree.search(tree.getRoot(), searchKey);
        if (found == null) {
            System.out.println("Key " + searchKey + " was not found in the binary search tree.");
        } else {
            System.out.println("Key " + searchKey + " was found in the binary search tree.");
        }
        return found;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Creation and setting of variables for use in testing.
        BSTNode temp1;
        BSTNode temp2;
        BSTNode temp3;
        BSTNode temp4;
        BSTNode temp5;
        BSTNode temp6;
        int[] array1 = {22, 3, 5, 9, 32, 27, 2, 37, 54, 48, 22, 11, 11, 12, 29, 31, 7, 18, 19, 51};
        int[] array2 = {7, 22, 6, 8, 10};
        int[] array3 = {18, 12, 14, 5, 16, 22, 21, 4, 11, 7};
        int[] array4 = {16, 16, 16, 24, 48, 21, 45, 23, 54, 45, 7, 12, 3, 43, 9};
        int[] array5 = {20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        tree = new BinarySearchTree();
        // Insertion of 20 elements into binary search tree.
        System.out.println("Size of the binary search tree before the insertion of 20 elements: " + tree.getSize());
        for (int i = 0; i < array1.length; i++) {
            tree.insert(new BSTNode(array1[i]));
        }
        System.out.println("Size of the binary search tree after the insertion of 20 elements: " + tree.getSize());
        // Traversals of the binary search tree.
        System.out.print("Pre-order traversal: ");
        tree.preOrder(tree.getRoot());
        System.out.print("\nIn-order traversal: ");
        tree.inOrder(tree.getRoot());
        System.out.print("\nPost-order traversal: ");
        tree.postOrder(tree.getRoot());
        // Printing by using minumum and successor.
        temp1 = tree.minimum(tree.getRoot());
        System.out.print("\nPrinting using minumum and successor: " + temp1.getKey() + " ");
        for (int i = 0; i < tree.getSize() - 1; i++) {
            temp1 = tree.successor(temp1);
            System.out.print(temp1.getKey() + " ");
        }
        // Printing by using maximum and predecessor.
        temp1 = tree.maximum(tree.getRoot());
        System.out.print("\nPrinting using maximum and predecessor: " + temp1.getKey() + " ");
        for (int i = 0; i < tree.getSize() - 1; i++) {
            temp1 = tree.predecessor(temp1);
            System.out.print(temp1.getKey() + " ");
        }
        // Searching for five keys in the tree.
        System.out.println("\n\nSearching for five keys present in the binary search tree: ");
        temp1 = searchTree(22); // The searchTree() method calls the search method in the BinarySearchTree class and prints whether or not the key was found.
        temp2 = searchTree(2);
        temp3 = searchTree(54);
        temp4 = searchTree(11);
        temp5 = searchTree(31);
        // Searching for five keys not in the tree.
        System.out.println("\nSearching for five keys not present in the binary search tree: ");
        temp6 = searchTree(10);
        temp6 = searchTree(14);
        temp6 = searchTree(6);
        temp6 = searchTree(0);
        temp6 = searchTree(72);
        // Deleting five elements from the tree.
        System.out.println("\nSize of the tree before deletion of five elements: " + tree.getSize());
        tree.delete(tree.getRoot(), temp1); // Deleting the nodes that were searched for and found in the binary search tree previously.
        tree.delete(tree.getRoot(), temp2);
        tree.delete(tree.getRoot(), temp3);
        tree.delete(tree.getRoot(), temp4);
        tree.delete(tree.getRoot(), temp5);
        System.out.println("Size of the tree after deletion of five elements: " + tree.getSize());
        // Five bstSort executions.
        System.out.println("\nTesting bstSort() with five arrays: ");
        System.out.print("Array 1: ");
        tree = new BinarySearchTree(); // Empty the tree for use with bstSort()
        bstSort(array1);
        System.out.print("\nArray 2: ");
        tree = new BinarySearchTree();
        bstSort(array2);
        System.out.print("\nArray 3: ");
        tree = new BinarySearchTree();
        bstSort(array3);
        System.out.print("\nArray 4: ");
        tree = new BinarySearchTree();
        bstSort(array4);
        System.out.print("\nArray 5: ");
        tree = new BinarySearchTree();
        bstSort(array5);
        System.out.println();
    }
}
