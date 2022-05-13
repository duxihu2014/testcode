package gupao.threads.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest implements Runnable{


  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    Executors.newSingleThreadExecutor();
    Executors.newSingleThreadScheduledExecutor();
    Executors.newScheduledThreadPool(5);
    Executors.newFixedThreadPool(5);
    Executors.newCachedThreadPool();

    executorService.execute(new ThreadPoolTest());
    executorService.shutdown();



  }

  @Override
  public void run() {
    System.out.println("hello world!");
  }
}
