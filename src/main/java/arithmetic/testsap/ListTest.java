package arithmetic.testsap;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
  public static void main(String[] args) {
      int a [] = {1,3};
      int b [] = {4,7,8};
      int newArray[] = merge(a,b);
    for (int i = 0; i < newArray.length; i++) {
      System.out.println(newArray[i]);
    }

  }

  private static int [] merge(int a[],int b[]) {
    int newArray[] = new int[a.length+b.length];
    int curVal= 0;
    int firstIndex = 0;
    int secondIndex = 0;
    while (firstIndex<a.length||secondIndex<b.length){
      if(firstIndex==a.length){
        curVal = b[secondIndex++];
      }else if(secondIndex==b.length){
        curVal = a[firstIndex++];
      }else if(a[firstIndex]<b[secondIndex]){
        curVal = a[firstIndex++];
      }else if(a[firstIndex]>b[secondIndex]){
        curVal = b[secondIndex++];
      }
      newArray[firstIndex+secondIndex-1] = curVal;
    }
    return newArray;
  }


//  class Solution {
//    public void merge(int[] A, int m, int[] B, int n) {
//      for (int i = 0; i != n; ++i) {
//        A[m + i] = B[i];
//      }
//      Arrays.sort(A);
//    }
//  }
//
//  作者：LeetCode-Solution
//  链接：https://leetcode-cn.com/problems/sorted-merge-lcci/solution/mian-shi-ti-1001-he-bing-pai-xu-de-shu-zu-by-leetc/
//  来源：力扣（LeetCode）
//  著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

}
