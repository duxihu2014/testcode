package arithmetic;

//26.删除排序数组中的重复项
//https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
public class Solution26 {
    public static int removeDuplicates(int[] nums) {
      if (nums == null || nums.length == 0) {
        return 0;
      }
    int p = 0;
    int q = 1;
    while(q < nums.length){
        if(nums[p] != nums[q]){
          if(q - p > 1){
            nums[p + 1] = nums[q];
          }
            p++;
        }
        q++;
    }
    return p + 1;
}

  public static void main(String[] args) {
      int[]nums = {1,1,2,2,3,4,2,3};
    int len = Solution26.removeDuplicates(nums);

    System.out.println("-------------------");
    for (int i=0;i<len;i++) {
      System.out.println(nums[i]);
    }
  }

}