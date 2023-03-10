package org.example.DataSharingBetweenThreads;

public class Example {
    public static void main(String[] args) throws InterruptedException {

        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

        incrementingThread.start();
        decrementingThread.start();

        incrementingThread.join();
        decrementingThread.join();
        //structured like this (start -> then join) everytime we start the program we have unpredictable result.
        // the core problem is that the inventoryCounter is shared object which makes "items"-member variable also shared, between the two threads
        //and the second problem is that the operations both threads are calling (increment(), decrement()) are happening at the same time.

        // the solution is, we have to provide atomic operations
        /*we mark the critical sections (increment(), decrement()) with the keyword "synchronized", that's how we restrict multiple threads to execute a method
        on a shared object (result -> only one thread at a time)*/

        System.out.println("We currently have " + inventoryCounter.getItem() + " items");
    }

    public static class IncrementingThread extends Thread {
        private InventoryCounter inventoryCounter;

        public IncrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                inventoryCounter.increment();
            }
        }
    }

    public static class DecrementingThread extends Thread {
        private InventoryCounter inventoryCounter;

        public DecrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                inventoryCounter.decrement();
            }
        }
    }

    private static class InventoryCounter {
        private int item = 0;

        //critical section
        public synchronized void increment() {
            item++;
        }

        //critical section
        public synchronized void decrement() {
            item--;
        }

        public synchronized int getItem() {
            return item;
        }
    }
}