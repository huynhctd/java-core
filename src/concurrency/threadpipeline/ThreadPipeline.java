package concurrency.threadpipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ThreadPipeline {
    private List<ApprovalProcess> processors = new ArrayList<>();

    private BlockingQueue inputQueue  = null;
    private BlockingQueue outputQueue = null;

    public ThreadPipeline addProcessor(ApprovalProcess processor){
        this.processors.add(processor);
        return this;
    }

    public BlockingQueue getInputQueue() {
        return this.inputQueue;
    }

    public BlockingQueue getOutputQueue() {
        return this.outputQueue;
    }

    public void runStep() {
        List<Thread> threadList = new ArrayList<>();
        for(int i=0; i < processors.size(); i++) {
            threadList.add(new Thread(processors.get(i)));
        }
        for (Thread thread : threadList) {
            thread.start();
        }
    }
}
