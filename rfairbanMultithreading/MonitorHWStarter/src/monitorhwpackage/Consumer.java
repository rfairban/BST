package monitorhwpackage;

public class Consumer extends Thread {
    public int sum = 0;
    // you may use a constructor to initialize any data fields.
    // The first line of your constructor must be
    // invoking the superclass constructor like this:
    // super();
    
    @Override
    public void run() {
        for (int i = 0; i < HWMain.NUM_ITEMS; i++) {
            // remove an item from the buffer using the monitor's remove method
            // then process the item to incoroporate it into your summation
            int temp = HWMain.monitor.remove();
            sum = sum + temp;
        }
        // print out the value of the summation of all the consumed items
        System.out.println("Total summation: " + sum);
    }
}
