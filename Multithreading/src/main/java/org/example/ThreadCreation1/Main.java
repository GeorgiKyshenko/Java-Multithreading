package org.example.ThreadCreation1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("We are in thread " + Thread.currentThread().getName());
            System.out.println("Current thread priority is " + Thread.currentThread().getPriority());
            throw new RuntimeException("Internal Exception");
        });

        thread.setName("Misbehaving thread");
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                //that handler will be called if exception occurs in our thread and did not get caught anywhere
                System.out.println("Critical error happened in  thread " + t.getName() +
                        " the error is " + e.getMessage());
            }
        });

        System.out.println("We are in thread " + Thread.currentThread().getName() + " before starting a new thread");
        thread.start();
        System.out.println("We are in thread " + Thread.currentThread().getName() + " after starting a new thread");
    }
}