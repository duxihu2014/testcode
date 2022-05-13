package arithmetic.week01;


//1. 移动零
public class Solution283 {

    public static void main(String[] args) {
        int nums[] = {0,1,0,3,12};
        Solution283 solution26 = new Solution283();
        solution26.moveZeroes_1(nums);
//        solution26.moveZeroes_2(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    public void moveZeroes_2(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] !=0 ){
                if(i != j){
                    nums[j] = nums[i];
                    nums[i] = 0;
                }
                j++;
            }
        }
    }

    public void moveZeroes_1(int[] nums) {
        int current = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]!=0){
                nums[current++] = nums[i];
            }
        }
        for (int i = current; i < nums.length; i++) {
                nums[i] = 0;
        }
    }


}