package javajuc.day2.arrayblockingqueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author:Hardy
 * @QQ:2937270766
 * @官网：http://www.yuandengta.com
 */
class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name + " ";
    }
}

class Produce extends Thread {
    private static int i = 0;
    private ArrayBlockingQueue<User> arrayQueue;

    public Produce(ArrayBlockingQueue<User> arrayQueue) {
        this.arrayQueue = arrayQueue;
    }


    @Override
    public void run() {
        try {
            while (i < 1000) {
                arrayQueue.put(new User("user" + i));
                System.out.println(Thread.currentThread().getName()+"存入了第"+(i+1)+"个对User对象");
                if(i++ % 100 == 0){
                    TimeUnit.SECONDS.sleep(5);
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consume implements Runnable{
    ArrayBlockingQueue<User> arrayQueue;
    public Consume(ArrayBlockingQueue<User> arrayQueue){
        this.arrayQueue = arrayQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                //如果queue为null，那么5秒之后再去队列中取数据
                User user = arrayQueue.poll(5, TimeUnit.SECONDS);
                if (user != null)
                    System.out.println(Thread.currentThread().getName() + "--consume --" + user);

            }
        } catch (InterruptedException e) {
            System.out.println("consume queue InterruptedException");
        }
    }
}

public class ProduceConsumeDemo {
    public static void main(String[] args) {
        ArrayBlockingQueue<User> queue = new ArrayBlockingQueue<>(10);
        new Produce(queue).start();
        for (int i = 0; i < 5; i++) {
            new Thread(new Consume(queue)).start();
        }
    }
}
