package javajuc.day2.delayedqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author:Hardy
 * @QQ:2937270766
 * @官网：http://www.yuandengta.com
 *
 * Delayed接口继承了Comparable接口，这是因为DelayedQueue中的元素需要进行排序，
 * 一般情况，我们都是按元素过期时间的优先级进行排序
 * 应用场景：定时关闭资源、缓存对象、超时处理等各种场景
 */
public class Student implements Runnable, Delayed {
    //姓名
    private String name;
    // 做试题时间
    private long costTime;
    // 完成时间
    private long finishedTime;

    public Student(String name,long costTime){
        this.name = name;
        this.costTime = costTime;
        this.finishedTime = costTime + System.currentTimeMillis();
    }
    @Override
    public int compareTo(Delayed o) {
        Student other = (Student)o;
        return costTime >= other.costTime ? 1 : -1;

    }

    @Override
    public void run() {
        System.out.println(name + "交卷，用时 "+ costTime/1000);
    }

    /**
     * getDelay()方法返回值
     * 就是队列元素被释放前的保持时间，如果返回0或者一个负值，
     * 就意味着该元素已经到期需要被释放，此时，delayedQueue会通过take()方法释放此对象
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return (finishedTime - System.currentTimeMillis());
    }
}
