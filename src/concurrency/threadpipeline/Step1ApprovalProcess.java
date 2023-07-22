package concurrency.threadpipeline;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Step1ApprovalProcess implements ApprovalProcess {
    private BlockingQueue<String> inputQueue = new ArrayBlockingQueue(10);

    private BlockingQueue<String> outputQueue = null;

    private volatile boolean shutdownRequested = false;
    private volatile Thread executingThread = null;

    @Override
    public BlockingQueue setInputQueue(BlockingQueue inputQueue) {
        this.inputQueue = inputQueue;
        return this.inputQueue;
    }

    @Override
    public BlockingQueue setOutputQueue(BlockingQueue outputQueue) {
        this.outputQueue = outputQueue;
        return this.outputQueue;
    }

    @Override
    public void requestShutdown() {
        this.shutdownRequested = true;
        this.executingThread.interrupt();
    }

    @Override
    public void run() {
        this.executingThread = Thread.currentThread();

        while(!this.shutdownRequested){
            String input = null;
            try {
                input = this.inputQueue.take();
                boolean result = processStep1(input);

                if (processStep1(input)) {
                    this.outputQueue.put(input);
                }

                System.out.println(Thread.currentThread().getName() + " " +"Step 1: " + input + " => " + " result : " + result);
            } catch (InterruptedException e) {
                if(!this.shutdownRequested) {
                    System.out.println("Step 1: Failed for input " + input);
                    e.printStackTrace();
                }
            }
        }
    }

    boolean processStep1(String input) throws InterruptedException {
        String[] strings = input.split(",");
        Thread.sleep(1000);
        if (strings[0].equals("pass")) return true;
        return false;
    }
}
