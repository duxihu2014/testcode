package arithmetic.array;

import java.util.Arrays;
import java.util.Vector;

public class Solution01 {

  public static void main(String[] args) {
    Solution01 solution01 = new Solution01();
    int [] nums = {0 ,0,1,2,2,3};
    //System.out.println(solution01.removeDuplicates(nums));
    System.out.println(solution01.removeDuplicates2(nums));
    for (int i = 0; i < nums.length; i++) {
      System.out.println(nums[i]);
    }
  }
  int removeDuplicates(int[] nums){
    if(nums.length==0) return 0;
    int current = 0;
    for (int index = 0; index < nums.length; index++) {
          if(nums[index]!=nums[current]){
              nums[++current] = nums[index];
          }
    }
    return current+1;
  }

  int removeDuplicates2(int[] nums){
    if(nums.length==0) return 0;
    int cnt = 0;
    for (int i = 1; i < nums.length; i++) {
      if(nums[i]==nums[i-1]){
        ++cnt;
      }else
        nums[i -cnt] = nums[i];
    }
    return nums.length-cnt;
  }

}
