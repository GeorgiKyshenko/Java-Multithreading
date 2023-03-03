package org.example.ThreadJoin;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Example {
    public static void main(String[] args) throws InterruptedException {
        List<Long> numbers = Arrays.asList(10000000000L, 3435L, 35435L, 2324L, 4656L, 23L, 2435L, 5566L);

        List<FactorialThread> threads = new ArrayList<>();

        for (long number : numbers) {
            threads.add(new FactorialThread(number));
        }

        for (Thread thread : threads) {
            thread.setDaemon(true); // if some thread take too long to calculate we want to stop the program, so we set all the threads as Daemon
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join(2000);
            // with join method the main thread does not terminate if other threads are not finished
            // in that case the main thread is in a "wait" state, waiting the other thread to be executed
            // if the process takes very long time we can add join(2000) to specify how much we want to wait for the execution of this thread
            //if the execution takes more than 2 secs (2000 millis), the thread is terminated.
        }

        for (int i = 0; i < numbers.size(); i++) {
            FactorialThread factorialThread = threads.get(i);
            if (factorialThread.isFinished()) {
                System.out.println("Factorial of " + numbers.get(i) + " is " + factorialThread.getResult());
            } else {
                System.out.println("The calculation of " + numbers.get(i) + " is still in progress");
            }
        }
    }

    public static class FactorialThread extends Thread {
        private long inputNumber;
        private BigInteger result;
        private boolean isFinished;

        public FactorialThread(long inputNumber) {
            this.inputNumber = inputNumber;
            result = BigInteger.ZERO;
            isFinished = false;
        }

        @Override
        public void run() {
            this.result = factorial(inputNumber);
            this.isFinished = true;
        }

        private BigInteger factorial(long inputNumber) {

            BigInteger tempResult = BigInteger.ONE;
            for (long i = inputNumber; i > 0; i--) {
                tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
            }
            return tempResult;
        }

        public boolean isFinished() {
            return isFinished;
        }

        public BigInteger getResult() {
            return result;
        }
    }
}
