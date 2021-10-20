package lessons.threads;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import javax.swing.JOptionPane;

public class ThreadsExample {

    public static void main(String[] args) {
        Members members = new Members();
        Thread jirayaThread = new Thread(new EmailDeliveryService(members), "Jiraya");
        Thread kakashiThread = new Thread(new EmailDeliveryService(members), "Kakashi");
        jirayaThread.start();
        kakashiThread.start();
        while (true) {
            String email = JOptionPane.showInputDialog("Type your email");
            if (email == null || email.isEmpty()) {
                members.close();
                break;
            }
            members.addEmail(email);
        }
    }

}

class EmailDeliveryService implements Runnable {

    private final Members members;

    public EmailDeliveryService(Members members) {
        this.members = members;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " preparing to run and deliver emails");
        while (members.isOpen() || members.pendingEmails() > 0) {
            try {
                String email = members.retrieveEmail();
                if (email == null) {
                    continue;
                }
                System.out.println(threadName + " sending email to " + email);
                Thread.sleep(2000);
                System.out.println(threadName + " successfully send the email to " + email);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("All emails were sent successfully!");
    }
}

class Members {

    private final Queue<String> emails = new ArrayBlockingQueue<>(10);
    private boolean open = true;

    public boolean isOpen() {
        return open;
    }

    public int pendingEmails() {
        synchronized (emails) {
            return emails.size();
        }
    }

    public String retrieveEmail() throws InterruptedException {
        System.out.println(getThreadName() + " checking if there are emails");
        synchronized (emails) {
            while (emails.size() == 0) {
                if (!isOpen()) {
                    return null;
                } else {
                    System.out.println(getThreadName() + " no emails found to send. waiting...");
                    emails.wait();
                }
            }
            return this.emails.poll();
        }
    }

    public void addEmail(String email) {
        synchronized (emails) {
            System.out.println(getThreadName() + " just added an email");
            this.emails.add(email);
            this.emails.notifyAll();
        }
    }

    public void close() {
        open = false;
        synchronized (emails) {
            System.out.println(getThreadName() + " Notifying all thread that emails are close");
            emails.notifyAll();
        }
    }

    private String getThreadName() {
        return Thread.currentThread().getName();
    }

}
