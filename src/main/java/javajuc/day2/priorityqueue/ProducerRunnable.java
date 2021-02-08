package javajuc.day2.priorityqueue;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @Author:Hardy
 * @QQ:2937270766
 * @官网：http://www.yuandengta.com
 * 排队
 */
public class ProducerRunnable implements Runnable {
    public static final String NAME = "明刚红李刘吕赵黄王孙朱曾游丽吴昊周郑秦丘";
    private Random random = new Random();
    private PriorityBlockingQueue<Human> queue;

    public ProducerRunnable(PriorityBlockingQueue<Human> queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        for(int i = 0;i < 20;i++){
            Human h = new Human(random.nextInt(10000),"小"+NAME.charAt(i));
            queue.put(h);
            System.out.println(h+"开始排队");
        }
    }
}
