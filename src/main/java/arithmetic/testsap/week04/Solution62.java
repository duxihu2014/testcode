package arithmetic.testsap.week04;

//62. 不同路径
//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
//
//  机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
//
//  问总共有多少条不同的路径？

public class Solution62 {

  public int uniquePaths(int m, int n) {
    int[][] dp = new int[m][n];
    for (int i = m-1; i >=0 ; i--) {
      for (int j = n-1; j >= 0 ; j--) {
        if (i == m - 1 || j == n - 1){
          dp[i][j] = 1;
        }
        else {
          dp[i][j] = dp[i+1][j] +dp[i][j+1];
        }
      }
    }

    return dp[0][0];
  }

  public int uniquePaths2(int m, int n) {
    int[][] dp = new int[m][n];
    for (int i =0; i <m; i++) {
      for (int j = 0; j <n ; j++) {
        if (i == 0 || j == 0){
          dp[i][j] = 1;
        }
        else {
          dp[i][j] = dp[i-1][j] +dp[i][j-1];
        }
      }
    }
    return dp[m-1][n-1];
  }

  public static void main(String[] args) {
    Solution62 solution49 = new Solution62();
    int m = 3;
    int n = 4;

    System.out.println(solution49.uniquePaths(m,n));
    System.out.println(solution49.uniquePaths2(m,n));
  }


}
