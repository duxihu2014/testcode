package arithmetic.week01;


//1. 两数之和
public class Solution1 {

    public static void main(String[] args) {
        int nums[] = {2,7,11,15};
        Solution1 solution26 = new Solution1();
        int target = 9;
        int resultNums[] = solution26.twoSum(nums,target);
        for (int i = 0; i < resultNums.length; i++) {
            System.out.println(resultNums[i]);
        }
    }


    public int[] twoSum(int[] nums, int target) {
        int resultNums[] = new int[2];
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = 1; j < nums.length; j++) {
                if(nums[i]+nums[j]==target){
                    resultNums[0]=i;
                    resultNums[1]=j;
                    break;
                }
            }
        }
        return resultNums;
    }
}