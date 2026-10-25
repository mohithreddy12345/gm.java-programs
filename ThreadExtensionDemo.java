class GoodMorningThread extends Thread {
    @Override
    public void run() {
        while (!isInterrupted()) {
            System.out.println("Good Morning");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }
}

class HelloThread extends Thread {
    @Override
    public void run() {
        while (!isInterrupted()) {
            System.out.println("Hello");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }
}

class WelcomeThread extends Thread {
    @Override
    public void run() {
        while (!isInterrupted()) {
            System.out.println("Welcome");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }
}

public class ThreadExtensionDemo {
    public static void main(String[] args) {
        GoodMorningThread t1 = new GoodMorningThread();
        HelloThread t2 = new HelloThread();
        WelcomeThread t3 = new WelcomeThread();

        t1.start();
        t2.start();
        t3.start();
    }
}