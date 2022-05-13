package gupao.threads.test;

/**
 	 * 现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行？
 	 * @author RJH
 	 * 2017年11月24日
 	 */
	public class JoinDemo {

		public static void main(String[] args) throws InterruptedException {
			//初始化线程t1,由于后续有匿名内部类调用这个对象,需要用final修饰
			final Thread t1 = new Thread(new Runnable() {

				@Override
				public void run() {
					System.out.println("t1 is running");
				}
			});
			//初始化线程t2,由于后续有匿名内部类调用这个对象,需要用final修饰
			final Thread t2 = new Thread(new Runnable() {

				@Override
				public void run() {
          System.out.println("t2 is running");
				}
			});
			//初始化线程t3
			Thread t3 = new Thread(new Runnable() {


          @Override
          public void run() {
            System.out.println("t3 is running");
          }
			});
			//依次启动3个线程
			t1.start();
			t1.join();
			t2.start();
			t2.join();
			t3.start();

		}
	}