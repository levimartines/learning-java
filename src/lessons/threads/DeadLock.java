package lessons.threads;

public class DeadLock {

    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();

        Runnable r1 = () -> {
            synchronized (lock1) {
                System.out.println("Thread 1 : Holding --- waiting for thread 2 finishes");
                synchronized (lock2) {
                    System.out.println("Thread 1: Holding lock 2");
                }
            }
        };

        Runnable r2 = () -> {
            synchronized (lock2) {
                System.out.println("Thread 2 : Holding --- waiting for thread 1 finishes");
                synchronized (lock1) {
                    System.out.println("Thread 2: Holding lock 1");
                }
            }
        };

        new Thread(r1).start();
        new Thread(r2).start();
    }
}
