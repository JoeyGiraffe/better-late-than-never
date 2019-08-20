package orz.joey.talkischeap;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class SyncThreadTest {


    class SyncThread extends Thread {
        private final AtomicInteger count = new AtomicInteger();

        private LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>();

        SyncThread() {
//            deque.add("a");
//            deque.add("b");
        }

        @Override
        public void run() {
            System.out.println(deque);
            String ele = deque.poll();
            System.out.println(getCurrentTime() + Thread.currentThread().getName() + " poll: " + ele);
            if (ele !=null) {
                System.out.println(getCurrentTime() + Thread.currentThread().getName() + " lock:" + ele);
                return;
            }
            synchronized(this) {
                try {
                    Thread.sleep(500);
                    deque.add(String.valueOf(count.incrementAndGet()));
                    System.out.println(getCurrentTime() + Thread.currentThread().getName() + " add: " + count.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                String take = deque.take();
                System.out.println(Thread.currentThread().getName() + " lock:" + take);
                Thread.sleep(500);
                deque.add(take);
                System.out.println(Thread.currentThread().getName() + " unlock:" + take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private String getCurrentTime() {
            return "【"+new SimpleDateFormat("mm:ss").format(new Date())+"】";
        }

        public AtomicInteger getCount() {
            return count;
        }
    }

    public static void main(String[] args) {
        SyncThreadTest syncThreadTest = new SyncThreadTest();
        SyncThread syncThread = syncThreadTest.new SyncThread();
        for (int i=0; i<5; i++) {
            new Thread(syncThread, "thread_" + i).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i=5; i<10; i++) {
            new Thread(syncThread, "thread_" + i).start();
        }
    }
}
