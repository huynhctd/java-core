package concurrency.blockingqueue;

public class Producer implements Runnable {

    public Producer(BlockingQueueImpl<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }
    private BlockingQueueImpl<String> blockingQueue;


    @Override
    public void run() {
        while (true) {
            try {
                String event = Thread.currentThread().getName();
                blockingQueue.enqueue(event);
                System.out.println("Producing: " + event);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
