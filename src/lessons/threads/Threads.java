package lessons.threads;

/*
* User Threads : High priority threads that JVM wait to complete its task before terminating it.
* Daemon Threads : is a thread that not prevent the JVM from exiting when the program finishes but the thread is still running.
* */
public class Threads {

    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadImpl('A'));
        Thread t2 = new Thread(new ThreadImpl('B'));
        Thread t3 = new Thread(new ThreadImpl('C'));

        t1.start();
        t2.start();
        t3.start();
    }
}

class ThreadImpl implements Runnable {
    char c;
    public ThreadImpl(char c) {
        this.c = c;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        for (int i=1; i <= 500; i ++) {
            System.out.print(c);
            if (i % 100 == 0) {
                System.out.println();
            }
        }
    }
}
