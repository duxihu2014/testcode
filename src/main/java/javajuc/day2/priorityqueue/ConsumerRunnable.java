package javajuc.day2.priorityqueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @Author:Hardy
 * @QQ:2937270766
 * @官网：http://www.yuandengta.com
 * 办理业务
 */
public class ConsumerRunnable implements Runnable {
    private PriorityBlockingQueue<Human> queue;
    public ConsumerRunnable(PriorityBlockingQueue<Human> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            Human take = queue.poll();
            if (take == null){
                break;
            }
            System.out.println(take + " 办理业务.");
        }
    }
}
