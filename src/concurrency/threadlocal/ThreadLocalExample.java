package concurrency.threadlocal;

public class ThreadLocalExample {
    private ThreadLocal<String> threadLocal =  new ThreadLocal<>() {
        @Override
        protected String initialValue() {
            return String.valueOf(System.currentTimeMillis());
        }
    };

}
