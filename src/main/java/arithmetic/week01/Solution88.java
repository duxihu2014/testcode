package arithmetic.week01;


//88. 合并两个有序数组
public class Solution88 {

    public static void main(String[] args) {
        int nums1[] = {1,2,3,0,0,0};
        int nums2[] = {2,5,6};
        Solution88 solution26 = new Solution88();
        solution26.merge(nums1,3,nums2,3);
        for (int i = 0; i < nums1.length; i++) {
            System.out.println(nums1[i]);
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int j=0;
        for (int i = m; i <nums1.length ; i++) {
            nums1[i]=nums2[j];
            j++;
        }

        int tmp = 0;
        for (int i = 0; i <nums1.length -1; i++) {
            for (int k = i+1; k <nums1.length ; k++) {
                if(nums1[i] > nums1[k]){
                    tmp = nums1[i];
                    nums1[i] = nums1[k];
                    nums1[k] = tmp;
                }
            }
        }

    }


}