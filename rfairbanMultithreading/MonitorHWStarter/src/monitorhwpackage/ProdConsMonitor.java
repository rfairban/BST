package monitorhwpackage;

public class ProdConsMonitor {
    // data fields for the shared buffer, a stack of fixed size based on the
    // static final BUFFER_SIZE in HWMain.
    // Use an array of that length, and an index for the top index
    // which contains an item.
    public int[] buffer = new int[HWMain.BUFFER_SIZE];
    public int bufferLength = -1;
    // You will also want to keep track of how many times
    // the insert blocks, and how many times the remove blocks.
    public int insertBlocks = 0;
    public int removeBlocks = 0;
    // The constructor initializes all data fields
    
    // insert an int value into the buffer
    public synchronized void insert(int value) {
        if (bufferLength == HWMain.BUFFER_SIZE - 1) { // buffer is full; can't insert.
            // note that we are blocking during insert
            insertBlocks++;
            goToSleep();
        }
        //critical section code here. insert into the stack and adjust the index
        bufferLength++;
        buffer[bufferLength] = value;
        if (bufferLength == 0) { // we just inserted the only item into a previously empty buffer
            notify(); // wake a sleeping thread that waited from inside this monitor
        }
    }

    // remove an item from the buffer, and return its value as an int
    public synchronized int remove() {
        int item;
        if (bufferLength == -1) { // buffer is empty -- nothing to remove
            // note that we are blocking during remove
            removeBlocks++;
            goToSleep();
        }
        // critical section code here. remove item from buffer and adjust the index
        item = buffer[bufferLength];
        bufferLength--;
        if (bufferLength == HWMain.BUFFER_SIZE-2) { // we just removed an item from a previously full buffer
            notify(); // wake a sleeping thread that waited from inside this monitor
        }
        return item;
        // finally, return the item that was taken from the buffer
    }

    // Jacketing for thread wait. DO NOT MODIFY
    private synchronized void goToSleep() {
        try {
            wait();
        } catch (InterruptedException e) {
            // do nothing
        }
    }

    // Make public synchronized getter methods for the number of times insert has blocked,
    // and the number of times remove has blocked.
    public int getInsertBlocks(){
        return insertBlocks;
    }
    public int getRemoveBlocks(){
        return removeBlocks;
    }
}