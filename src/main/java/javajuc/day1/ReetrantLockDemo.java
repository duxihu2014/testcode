package javajuc.day1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReetrantLockDemo {
    private Lock lock = new ReentrantLock();

    public void demo(){
        //获取锁
        lock.lock();
        System.out.println("begin demo");
        //执行demo2方法
        demo2();
        //释放锁
        lock.unlock();
    }

    public void demo2(){
        //获取锁
        lock.lock();
        System.out.println("begin demo2");

        //释放锁
        lock.unlock();
    }

    public static void main(String[] args) {
        ReetrantLockDemo demo = new ReetrantLockDemo();
        demo.demo();
    }

}
