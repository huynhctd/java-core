package concurrency.blockingqueue;

public class Consumer implements Runnable{
    private BlockingQueueImpl<String> blockingQueue;

    private String event;

    public Consumer(BlockingQueueImpl<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String value = blockingQueue.dequeue();
                System.out.println("Consuming: " + Thread.currentThread().getName() + " " + value);
                Thread.sleep(5000); // Sleep to simulate some processing time before consuming the next item
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
