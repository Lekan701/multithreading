package org.example.basicMultithreading;

public class DaemonUserThreadDemo {
    public static void main(String[] args) {
        Thread bgThread = new Thread(new DaemonHelper());
        Thread usrThread = new Thread(new UserThreadHelper());

        bgThread.setDaemon(true);

        bgThread.start();
        usrThread.start();

        // User thread given priority
        // Daemon thread run in background
        // Once usr thread is done JVM shutsdown and daemon is forced to shutdown also
    }
}

class DaemonHelper implements Runnable{
    @Override
    public void run() {
        int count = 0;
        while (count < 500){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
            System.out.println("Daemon helper running ... ");
        }
    }
}

class UserThreadHelper implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User thread done with execution!");
    }
}