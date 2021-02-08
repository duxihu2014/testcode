package javajuc.day2.delayedqueue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:Hardy
 * @QQ:2937270766
 * @官网：http://www.yuandengta.com
 */
public class Teacher {
    public static void main(String[] args) throws InterruptedException {
        Random r = new Random();
        // 把所有学生看作是一个延迟队列
        DelayQueue<Student> students = new DelayQueue<>();
        //构造一个线程池用来让学生考试
        ExecutorService exec = Executors.newFixedThreadPool(30);
        for(int i = 0;i < 30;i++){
            //初始化学生的姓名和做题时间
            students.put(new Student("学生"+(i+1),3000+r.nextInt(10000)));
        }
        //开始考试
        while(!students.isEmpty()){
            exec.execute(students.take());
        }
        exec.shutdown();
    }
}
