package concurrency.threadpipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPipelineMain {
    public static void main(String[] args) {
        List<String> inputs = new ArrayList<>();
        inputs.add("pass,pass");
        inputs.add("1,pass");
        inputs.add("2,pass");
        inputs.add("pass,3");
        inputs.add("pass,4");
        inputs.add("pass,5");
        inputs.add("6,pass");
        inputs.add("7,pass");
        inputs.add("8,pass");
        inputs.add("pass,9");

        BlockingQueue queue1 = new ArrayBlockingQueue(10);
        BlockingQueue queue2 = new ArrayBlockingQueue(10);

        BlockingQueue finalOutputQueue = new ArrayBlockingQueue(10);

        Step1ApprovalProcess processor1 = new Step1ApprovalProcess();
        Step2ApprovalProcess processor2 = new Step2ApprovalProcess();

        processor1.setInputQueue (queue1);
        processor1.setOutputQueue(queue2);
        processor2.setInputQueue (queue2);
        processor2.setOutputQueue(finalOutputQueue);

        Thread thread1 = new Thread(processor1);
        Thread thread2 = new Thread(processor2);

        thread1.start();
        thread2.start();

        for(int i=0; i<10; i++) {
            try {
                queue1.put(inputs.get(i) );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(int i=0; i<10; i++) {
            try {
                Object result = finalOutputQueue.take();
                System.out.println("Result: " + result);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Done");
        processor1.requestShutdown();
        processor2.requestShutdown();
    }
}
