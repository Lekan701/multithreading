package org.example.sequential;

public class SequentialExecutionDemo {

    public static void main(String[] args) {
        // One single thread created by the jvm is running these methods
        // Methods called and run sequentially, demo 2 waits until demo 1 is done as seen in output
        demo1();
        demo2();
    }

    private static void demo1(){
        for (int i = 0; i < 5; i++){
            System.out.println("From demo 1 " + i);
        }
    }
    private static void demo2(){
        for (int i = 0; i < 5; i++){
            System.out.println("From demo 2 " + i);
        }
    }

}
