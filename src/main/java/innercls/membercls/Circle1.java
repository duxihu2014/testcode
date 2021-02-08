package innercls.membercls;

class Circle1 {
    private double radius = 0;
    public static  int count = 1;


     
    public Circle1(double radius) {
        this.radius = radius;
    }
     
    class Draw {     //内部类
        public void drawSahpe() {
          System.out.println("drawshape");
          System.out.println(radius);
          System.out.println(count);
        }

    }
}