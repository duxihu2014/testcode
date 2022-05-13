package gupao.threads.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

public class CallableTest implements Callable<String> {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    Future<String> future = scheduledExecutorService.submit(new CallableTest());
    System.out.println(future.get());
  }

  @Override
  public String call() throws Exception {
    return "Hello";
  }
}
