package gupao.threads.test;

public class VolatileTest {


  static boolean stop = false;
  public static void main(String[] args) throws InterruptedException {
    VolatileTest.test4();

    Thread thread=new Thread(()->{
      int i=0;
      while(!stop){
        i++;


// System.out.println("rs:"+i);





//        synchronized (VolatileTest.class){
//        }

        //new File("txt.txt");
        try {
          Thread.sleep(0);//线程切换 同yeild()
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

      }
    });
    thread.start();
    Thread.sleep(1000);
    stop=true;
  }


  //static boolean stop1 = false;
  static volatile boolean stop1 = false;
  public  void test1() throws InterruptedException {
    Thread thread=new Thread(()->{
      int i=0;
      while(!stop1){
        i++;
      }
      // System.out.println("rs:"+i);
    });
    thread.start();
    Thread.sleep(1000);
    stop1=true;
  }


  static volatile int i = 0;
  public  void test2() throws InterruptedException {
    Thread thread=new Thread(()->{
      int i=0;
      while(!stop1){
        i++;
      }

    });
    thread.start();
    Thread.sleep(1000);
    stop1=true;
  }



  //static boolean stop1 = false;
  static volatile boolean stop3 = false;
  public static void test4() throws InterruptedException {
    stop3 = true;
  }




}
