package org.example.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorDemo {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        try {
            for (int i = 0; i < 5; i++) {
                service.execute(new Task(i));
            }
        } finally {
            // Shutdown the executor service
            service.shutdown();
        }
    }

    //This implementation of Executor Service means we do not have to deal with the creation of 5 seperate threads
    //Assigning the runnable tasks to 5 different threads and starting them all
    //ExecutorService handles it all for us

}

class Task implements Runnable {
    private final int taskId;

    public Task(int taskId){
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("Task with ID " + taskId + " being executed by Thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex){
            throw new RuntimeException(ex);
        }
    }
}
