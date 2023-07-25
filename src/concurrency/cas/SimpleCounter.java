package concurrency.cas;

public class SimpleCounter implements Counter {
    private MyLock lock;

    public SimpleCounter(MyLock lock) {
        this.lock = lock;
    }

    private long count = 0;

    public void inc() {
        this.lock.lock();
        this.count++;
        this.lock.unlock();

    }

    public long getCount() {
        return this.count;
    }
}
