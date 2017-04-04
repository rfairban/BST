package quicksort;
import java.util.Random;
import java.util.Arrays;
/**
 * @author Ryan Fairbanks
 * @version 9/13/16
 * @description CSCI 333: Data Structures. Homework 3: Quicksort. Implements
 * quicksort sorting method and the randomized quicksorting sorting method.
 * Each is tested five times, once each with each array, and with the results
 * printed to ensure the sort was completed.
 */
public class Homework3 {
    protected int[] array; // Stores the original and quicksort-sorted array.
    protected int[] randomArray; // Stores the original and randomizedQuicksort-sorted array.
    public Homework3(int[] array){
        this.array = array;
    }
    public void quicksort(int start, int end){ // Inclusive upper bound.
        if(start < end){ // Base case is the implied empy else case.
            int split = this.partition(start, end); // Split into subproblems. Location stored in split.
            this.quicksort(start, split-1); // First bucket (subproblem)
            this.quicksort(split+1, end); // Second bucket (subproblem)
        }
    }
    private int partition(int start, int end){
        int pivot = this.array[end]; // Pivot value at start.
        int index = start - 1;
        int temp; // Holds value used for the swaps.
        for(int i = start; i <= end - 1; i++){ //Tansverse subarray.
            if(this.array[i] <= pivot){ // Determine if value is less than pivot.
                index = index + 1;
                temp = this.array[i];
                this.array[i] = this.array[index];
                this.array[index] = temp; // Found lesser value goes left.
            }
        }
        temp = this.array[index+1];
        this.array[index+1] = this.array[end]; // Place pivot inbetween right and left sides.
        this.array[end] = temp;
        return index + 1; // Return index for quicksort to generate two subproblems.
    }
    public void randomizedQuicksort(int start, int end){
        if(start < end){
            Random rand = new Random();
            int pivot = rand.nextInt((end - start) + 1) + start; // Generate random privot point from between provided start and end.
            int temp = this.array[pivot];
            this.array[pivot] = this.array[end];
            this.array[end] = temp;
            int split = this.partition(start, end); // Split into subproblems. Location stored in split.
            this.quicksort(start, split-1); // First bucket (subproblem)
            this.quicksort(split+1, end); // Second bucket (subproblem)
        }
    }
    public void testSort(){
        int start = 0; // Set starting and ending indexes.
        int end = this.array.length - 1;
        int[] temp = Arrays.copyOf(this.array, this.array.length); // Store temp to reset and try with randomized sort.
        System.out.println("Original array: " + Arrays.toString(this.array)); // Print original array.
        this.quicksort(start, end);
        System.out.println("Quicksorted array: " + Arrays.toString(this.array));
        this.array = Arrays.copyOf(temp, temp.length); // Reset array to previously unsorted state.          
        System.out.println("Reset original array: " + Arrays.toString(this.array));
        this.randomizedQuicksort(start, end);
        System.out.println("Randomized quicksorted array: " + Arrays.toString(this.array));
    }
    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 4, 5}; // Arrays to be quicksorted.
        int[] array2 = {5, 4, 3, 2, 1};
        int[] array3 = {12, 11, 29, 33, 37, 2};
        int[] array4 = {22, 34, 15, 14, 21, 29, 52};
        int[] array5 = {9, 18, 14, 34, 7, 56, 28, 42, 3};
        Homework3 test1 = new Homework3(array1); // Create instances to test.
        Homework3 test2 = new Homework3(array2);
        Homework3 test3 = new Homework3(array3);
        Homework3 test4 = new Homework3(array4);
        Homework3 test5 = new Homework3(array5);
        System.out.println("test1: ");
        test1.testSort();
        System.out.println("\ntest2: ");
        test2.testSort();
        System.out.println("\ntest3: ");
        test3.testSort();
        System.out.println("\ntest4: ");
        test4.testSort();
        System.out.println("\ntest5: ");
        test5.testSort();
    }
}