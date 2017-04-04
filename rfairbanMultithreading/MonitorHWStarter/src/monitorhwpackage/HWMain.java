/*
 * @author Ryan Fairbanks
 * CSCI 331: Operating Systems
 *
 * Starter code provided by Adam Whitley. Code creates two threads: one to add 
 * values to a buffer of the default size below and the other to remove the 
 * values and use them to calculate the total once the buffer has reached 
 * capacity. The process is repeated until a total of 10,000,000 numbers have 
 * been through the buffer.
 */
package monitorhwpackage;

public class HWMain {

    public static final int BUFFER_SIZE = 100; // buffer size
    public static final int NUM_ITEMS = 10000000;
    public static final ProdConsMonitor monitor = new ProdConsMonitor();
    // The ProdConsMonitor class's object can be a static field in this main class
    public static void main(String[] args) throws InterruptedException {
        // create producer and consumer objects.
        Producer p = new Producer();
        Consumer c = new Consumer();
        // start their threads, join their threads, and then finally
        p.start();
        c.start();
        p.join();
        c.join();
        // print out how many times the monitor insert blocked
        // and how many times the monitor remove blocked.
        System.out.println("Number of insert blocks: " + monitor.getInsertBlocks());
        System.out.println("Number of remove blocks: " + monitor.getRemoveBlocks());
    }

}
