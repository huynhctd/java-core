package concurrency.blockingqueue;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueueImpl<T> {
    private List<T> queue = new LinkedList();
    private int  limit = 10;

    public BlockingQueueImpl(int limit){
        this.limit = limit;
    }


    public synchronized void enqueue(T item)
            throws InterruptedException  {
        while(this.queue.size() == this.limit) {
            wait();
        }
        this.queue.add(item);
        if(this.queue.size() == 1) {
            notifyAll();
        }
    }


    public synchronized T dequeue()
            throws InterruptedException{
        while(this.queue.size() == 0){
            wait();
        }
        if(this.queue.size() == this.limit){
            notifyAll();
        }

        return this.queue.remove(0);
    }
}
