package concurrency.semaphore;

import java.util.concurrent.Semaphore;

public class MeetingRoomExample {
    // max count user access to meeting room
    private static final int POOL_SIZE = 5;
    private final Semaphore semaphore = new Semaphore(POOL_SIZE);

    public void enterPool(String person) {
        try {
            semaphore.acquire();
            System.out.println(person + " entered the meeting room.");
            // Simulate swimming time
            Thread.sleep(2000);
            System.out.println(person + " left the meeting room.");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MeetingRoomExample pool = new MeetingRoomExample();

        for (int i = 1; i <= 10; i++) {
            String person = "Person " + i;
            Thread thread = new Thread(() -> pool.enterPool(person));
            thread.start();
        }
    }
}
