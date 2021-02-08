package arithmetic.week01;


//189. 旋转数组
public class Solution189 {

    public static void main(String[] args) {
        int nums[] = {1,2,3,4,5,6,7};
        Solution189 solution26 = new Solution189();
        int k =3;
        solution26.rotate(nums,k);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }

    }
    public void rotate(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            int tmp =  nums[0];
           nums[0] =nums[nums.length-1];
           for(int j=nums.length-1;j>1;j--){
               nums[j] = nums[j-1];
           }
            nums[1] = tmp;
        }
    }
}