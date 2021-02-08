package arithmetic.week01;


//26. 删除排序数组中的重复项
public class Solution26 {

    public static void main(String[] args) {
        int nums[] = {0,0,1,1,1,2,2,3,3,4};
        Solution26 solution26 = new Solution26();
        int curentsize = solution26.removeDuplicates(nums);
        for (int i = 0; i < curentsize; i++) {
            System.out.println(nums[i]);
        }

    }

    public int removeDuplicates(int[] nums) {
        int current = 1;
        int faster = 1;
        int currentValue = nums[0];
        for (int i = 1; i < nums.length; i++) {
                if(currentValue!=nums[i]){
                    nums[current++]=nums[i];
                    currentValue =nums[i];
                }
                    faster++;

        }
        return current;
    }
}