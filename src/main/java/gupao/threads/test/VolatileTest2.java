package gupao.threads.test;

public class VolatileTest2 extends Thread{

  private int i =0;
  private boolean flag = false;
  @Override
  public void run() {
      while (!flag){
        i++;
      }
  }

  public static void main(String[] args) throws InterruptedException {
    VolatileTest2 vt = new VolatileTest2();
    vt.start();

    Thread.sleep(2000);
    vt.flag = true;

    System.out.println(vt.i);//TODO:代码有问题？？？
  }
}
