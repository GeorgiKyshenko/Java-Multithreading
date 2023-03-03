package org.example.ThreadCreation2;

public class Main2 {
    public static void main(String[] args) {
        Thread thread = new NewThread();
        thread.setName("Thread example2");
        thread.start();

    }

    private static class NewThread extends Thread {
        @Override
        public void run() {
            System.out.println("Current thread is " + Thread.currentThread().getName()); // we can change Thread.currentThread with this.currentThread
        }
    }
}
