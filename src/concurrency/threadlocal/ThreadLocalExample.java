package concurrency.threadlocal;

public class ThreadLocalExample {
    public static void main(String[] args) throws InterruptedException {

        Thread thread1 =  new Thread(new RequestHandler("1,huynhctd"));
        thread1.setName("Thread-1");
        Thread thread2 =  new Thread(new RequestHandler("2,namtd"));
        thread2.setName("Thread-2");
        thread1.start();
        thread2.start();
    }
}
