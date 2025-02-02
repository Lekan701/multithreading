package org.example.threadSynchronisation;

public class SynchronisationDemo {

    private static int counter1 = 0;
    private static int counter2 = 0;


    public static void main(String[] args) {
        Thread one = new Thread(() -> {
            for(int i = 0; i  < 10000; i++) {
                increment1();
            }
        });

        Thread two = new Thread(() -> {
            for(int i = 0; i  < 10000; i++) {
                increment2();
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


        System.out.println("Counter value : " + counter1 + " -- " + counter2); // Expected output 20000
    }

    // synchronized keyword tells jvm to allow this method to be accessed by only one thread at a time at any cost
    private synchronized static void increment1(){
        counter1++; // this is a critical section
        //limiting its access by only one thread at a time
    }

    private synchronized static void increment2(){
        counter2++; // this is a critical section
        //limiting its access by only one thread at a time
    }

    //The increment methods due to it being static is going to cause entire class to be locked
    //This means that the thread for operating on counter1 even though it has nothing to do with counter2 is locked
    //due to class locking
    // this is why synchronised in the method definition is not good for static methods

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
