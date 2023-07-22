package concurrency.threadsignaling;

public class FileProcessingWorkflow {
    private final Object lock = new Object();
    private boolean step1Complete = false;
    private boolean step2Complete = false;

    public void startWorkflow() {
        Thread step1Thread = new Thread(new Step1());
        Thread step2Thread = new Thread(new Step2());
        Thread step3Thread = new Thread(new Step3());

        step1Thread.start();
        step2Thread.start();
        step3Thread.start();
    }

    class Step1 implements Runnable {
        @Override
        public void run() {
            // Simulate file downloading and saving to disk
            System.out.println(Thread.currentThread().getName() + " Step 1: Downloading file and saving to disk...");
            try {
                Thread.sleep(2000); // Simulate time-consuming file download and save
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                step1Complete = true;
                lock.notifyAll(); // Notify other threads that Step 1 is complete
            }
        }
    }

    class Step2 implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                // Wait for Step 1 to complete before proceeding
                while (!step1Complete) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            // Perform file processing
            System.out.println(Thread.currentThread().getName() + " Step 2: Processing file...");
            try {
                Thread.sleep(3000); // Simulate time-consuming file processing
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                step2Complete = true;
                lock.notifyAll(); // Notify other threads that Step 2 is complete
            }
        }
    }

    class Step3 implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                // Wait for Step 2 to complete before proceeding
                while (!step2Complete) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            // Perform file upload
            System.out.println(Thread.currentThread().getName() + " Step 3: Uploading processed file...");
            try {
                Thread.sleep(1500); // Simulate time-consuming file upload
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        FileProcessingWorkflow workflow1 = new FileProcessingWorkflow();
        FileProcessingWorkflow workflow2 = new FileProcessingWorkflow();
        workflow1.startWorkflow();
        workflow2.startWorkflow();
    }
}
