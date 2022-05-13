package arithmetic.testsap;


//实现一个int整型数字的反转
//  https://blog.csdn.net/nwpu_geeker/article/details/79598131
public class ReverseNum {
    public static  int reverse(int x) {
        long result = 0;
        while (x != 0){
            result = result * 10 + x % 10;
            x = x / 10;
        }
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE){
            return 0;
        }
        return (int)result;
    }

  public static void main(String[] args) {
    System.out.println(ReverseNum.reverse(123));
  }
}