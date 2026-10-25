import java.util.LinkedList;

class PC {
    private final LinkedList<Integer> list = new LinkedList<>();
    private final int capacity = 2;
    private int value = 0;

    // Producer method to add items to the buffer
    public void produce() throws InterruptedException {
        while (true) {
            synchronized (this) {
                // If the buffer is full, the producer waits
                while (list.size() == capacity) {
                    wait();
                }

                // Add an item to the buffer
                list.add(value);
                System.out.println("Producer produced-" + value);
                value++;

                // Notify the consumer that there are items to consume
                notify();

                // Sleep for 1 second
                Thread.sleep(1000);
            }
        }
    }

    // Consumer method to remove items from the buffer
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                // If the buffer is empty, the consumer waits
                while (list.size() == 0) {
                    wait();
                }

                // Remove the first item from the buffer
                int val = list.removeFirst();
                System.out.println("Consumer consumed-" + val);

                // Notify the producer that space is available
                notify();

                // Sleep for 1 second
                Thread.sleep(1000);
            }
        }
    }
}

public class ProducerConsumerDemo {
    public static void main(String[] args) throws InterruptedException {
        // Create an instance of PC
        PC pc = new PC();

        // Create producer thread using a Lambda expression
        Thread producerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Create consumer thread using a Lambda expression
        Thread consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Start both threads
        producerThread.start();
        consumerThread.start();

        // Join both threads
        producerThread.join();
        consumerThread.join();
    }
}