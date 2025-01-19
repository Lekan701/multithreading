package org.example.threadSynchronisation;

public class SynchronisationDemo {

    private static int counter = 0;
    public static void main(String[] args) {
        Thread one = new Thread(() -> {
            for(int i = 0; i  < 10000; i++) {
                increment();
            }
        });

        Thread two = new Thread(() -> {
            for(int i = 0; i  < 10000; i++) {
                increment();
            }
        });

        one.start();
        two.start();

        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Counter value : " + counter); // Expected output 20000
    }

    // synchronized keyword tells jvm to allow this method to be accessed by only one thread at a time at any cost
    private synchronized static void increment(){
        counter++; // this is a critical section
        //limiting its access by only one thread at a time
    }

}

/*
count++; when broken down is 3 separate operations
1. Load the count value into memory
2. Increment the value
3. To save the value back as count
Inside of the first thread -> counter = 0; incrementValue = 1; Now thread is being interrupted and switched out for thread two
because it fails to save the new value, counter is not being updated correctly leading to the wrong counter output final value
thread 2 meets counter still at 0 and will do the count++ operation again.

This is called a race condition, two threads working on the same resource and order flow not being controlled properly

To fix this race condition, make sure one thread is accessing the shared resource at a time and completing its operation.
This is called mutual exclusion.
* */
