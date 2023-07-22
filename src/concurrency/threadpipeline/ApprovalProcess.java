package concurrency.threadpipeline;

import java.util.concurrent.BlockingQueue;

public interface ApprovalProcess extends Runnable {
    public BlockingQueue setInputQueue(BlockingQueue inputQueue);

    public BlockingQueue setOutputQueue(BlockingQueue outputQueue);

    public void requestShutdown();
}
