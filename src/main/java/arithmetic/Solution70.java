package arithmetic;

//70. 爬楼梯
//https://leetcode-cn.com/problems/climbing-stairs/
public class Solution70 {
  public int climbStairs(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;
    for(int i = 2; i <= n; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
  }


  public static void main(String[] args) {
    Solution70 solution70 = new Solution70();
    System.out.println(solution70.climbStairs(4));
  }

}