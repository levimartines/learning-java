package lessons.threads;

public class SynchronizingThreads {

    private Account account = new Account();

    public static void main(String[] args) {
        AccountThread runnable = new AccountThread();
        Thread t1 = new Thread(runnable, "Hestia");
        Thread t2 = new Thread(runnable, "Carlos");
        t1.start();
        t2.start();
    }

}

class AccountThread implements Runnable {

    private final Account account = new Account();

    private void withdraw(int amount) {

        System.out.println(getThreadName() + " OUTSIDE SYNCHRONIZED");

        /*
        * synchronized keyword works with object-lock. It can also be used with classes or added in static methods.
        * Example: synchronized (Account.class)
        * */
        synchronized (account) {
            System.out.println(getThreadName() + " INSIDE SYNCHRONIZED");
            if (account.getBalance() >= amount) {
                System.out.println(getThreadName() + " is going to withdraw money");
                account.withdraw(amount);
                System.out.println(
                    getThreadName() + " finished withdrawing. Current balance: " + account
                        .getBalance());
            } else {
                System.out.println("Not enough balance for " + getThreadName() + " withdraw");
            }
        }
    }

    private String getThreadName() {
        return Thread.currentThread().getName();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            withdraw(10);
            if (account.getBalance() < 0) {
                System.out.println("SOMETHING VERY WRONG HAPPENED");
            }
        }
    }
}

class Account {

    private int balance = 50;

    public void withdraw(int amount) {
        this.balance = balance - amount;
    }

    public int getBalance() {
        return balance;
    }
}