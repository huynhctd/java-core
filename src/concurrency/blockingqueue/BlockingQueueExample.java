package concurrency.blockingqueue;

public class BlockingQueueExample {
    public static void main(String[] args) {
        BlockingQueueImpl<String> blockingQueue =  new BlockingQueueImpl(2);


        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);

        Thread producerThread1 = new Thread(producer, "producerThread1");
        Thread producerThread2 = new Thread(producer, "producerThread2");
        Thread producerThread3 = new Thread(producer, "producerThread3");

        Thread consumerThread1 = new Thread(consumer, "consumerThread1");
        Thread consumerThread2 = new Thread(consumer, "consumerThread2");
        Thread consumerThread3 = new Thread(consumer, "consumerThread3");
        Thread consumerThread4 = new Thread(consumer, "consumerThread4");
        Thread consumerThread5 = new Thread(consumer, "consumerThread5");
        Thread consumerThread6 = new Thread(consumer, "consumerThread6");


        producerThread1.start();
        producerThread2.start();
        producerThread3.start();

        consumerThread1.start();
        consumerThread2.start();
        consumerThread3.start();
        consumerThread4.start();
        consumerThread5.start();
        consumerThread6.start();
    }
}
