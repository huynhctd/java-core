package concurrency.threadpipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPipelineExample {
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


        ThreadPipeline threadPipeline = new ThreadPipeline();
        BlockingQueue<String> input =  new ArrayBlockingQueue<>(10);
        input.addAll(inputs);
        BlockingQueue<String> output1 =  new ArrayBlockingQueue<>(10);
        BlockingQueue<String> output2 =  new ArrayBlockingQueue<>(10);

        // Set up process 1
        Step1ApprovalProcess step1ApprovalProcess =  new Step1ApprovalProcess();
        step1ApprovalProcess.setInputQueue(input);
        step1ApprovalProcess.setOutputQueue(output1);

        // Set up process 2
        Step2ApprovalProcess step2ApprovalProcess =  new Step2ApprovalProcess();
        step2ApprovalProcess.setInputQueue(output1);
        step2ApprovalProcess.setOutputQueue(output2);


        threadPipeline.addProcessor(step1ApprovalProcess);
        threadPipeline.addProcessor(step2ApprovalProcess);



        threadPipeline.runSteps();
    }
}
