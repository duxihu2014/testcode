package arithmetic.testsap.week03;


//69. x 的平方根
//  实现 int sqrt(int x) 函数。
//
//  计算并返回 x 的平方根，其中 x 是非负整数。
//
//  由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
//
//  示例 1:
//
//  输入: 4
//  输出: 2
//  示例 2:
//
//  输入: 8
//  输出: 2
//  说明: 8 的平方根是 2.82842...,
//       由于返回类型是整数，小数部分将被舍去。

public class Solution69 {

  //方法1：二分查找
  public int mySqrt(int x) {
    if(x ==0 || x ==1){
      return x;
    }

    long left = 0, right = x;
    long mid = 1;
    while (left <= right) {
      mid = (right - left) / 2 + left;
       if (mid *mid > x) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return (int)right;
  }

  public static void main(String[] args) {
    Solution69 solution69 = new Solution69();
    System.out.println(solution69.mySqrt(8));

  }


}
