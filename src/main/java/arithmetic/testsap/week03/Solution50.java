package arithmetic.testsap.week03;



import java.util.ArrayList;
import java.util.List;

//50. Pow(x, n)
//实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
//
//   
//
//  示例 1：
//
//  输入：x = 2.00000, n = 10
//  输出：1024.00000
//  示例 2：
//
//  输入：x = 2.10000, n = 3
//  输出：9.26100
//  示例 3：
//
//  输入：x = 2.00000, n = -2
//  输出：0.25000
//  解释：2-2 = 1/22 = 1/4 = 0.25



public class Solution50 {

  //1.暴力法   O(n)
  public double myPow1(double x, int n) {
        double result = 1;
    for (int i = 0; i < n; i++) {
      result *=x;
    }
    return result;
  }


  //2.分治法
  // template: 1.terminator 2.process(split your big problem) 3.drill down (subproblems)
  //merge(subresult)  4.reverse states
  public double myPow2(double x, int n) {
    if(n<0) return 1.0/myPow2(x,-n);

    double res = 1.0;
    for (int i = n; i!=0; i/=2) {
      if(i%2 !=0){
        res *= x;
      }
      x *=x;
      //i=10   2 *=2  res =1.0
      //i-5   1.0 *=4   res =4.0
    }

    return res<0? 1/res:res;
  }


  public double quickMul(double x, long N) {
    if (N == 0) {
      return 1.0;
    }
    double y = quickMul(x, N / 2);
    return N % 2 == 0 ? y * y : y * y * x;
  }

  public double myPow3(double x, int n) {
    long N = n;
    return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
  }


  public static void main(String[] args) {
    Solution50 solution50 = new Solution50();
    System.out.println(solution50.myPow1(2,10));
    System.out.println(solution50.myPow2(2,10));//代码不理解
    System.out.println(solution50.myPow3(2,10));
  }


}
