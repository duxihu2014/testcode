package arithmetic.week01;


//66.加一
public class Solution66 {

    public static void main(String[] args) {
        int digits[] = {9,9};
        Solution66 solution26 = new Solution66();
        digits = solution26.plusOne(digits);
        for (int i = 0; i < digits.length; i++) {
            System.out.println(digits[i]);
        }
    }


    public int[] plusOne(int[] digits) {
        for (int i = digits.length-1; i >=0 ; i--) {
            digits[i]++;
            if(digits[i]%10!=0){
                return digits;
            }else {
                digits[i] = 0;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}