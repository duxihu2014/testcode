package innercls.other;

public class Test {
    public static void main(String[] args)  {
         
    }
     
    public void test(final int a) {
      final int b = 10;
        new Thread(){
            public void run() {
                System.out.println(a);
                System.out.println(b);

            };
        }.start();
    }
}