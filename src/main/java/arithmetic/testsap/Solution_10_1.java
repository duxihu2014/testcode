package arithmetic.testsap;

//面试题 10.01. 合并排序的数组
//  给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
//
//  初始化 A 和 B 的元素数量分别为 m 和 n。
//
//  示例:
//
//  输入:
//  A = [1,2,3,0,0,0], m = 3
//  B = [2,5,6],       n = 3
//
//  输出: [1,2,2,3,5,6]
//  说明:
//
//  A.length == n + m
//
//  来源：力扣（LeetCode）
//  链接：https://leetcode-cn.com/problems/sorted-merge-lcci
//  著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class Solution_10_1 {
    public void merge(int[] A, int m, int[] B, int n) {
        int pa = 0, pb = 0;
        int[] sorted = new int[m + n];
        int cur;
        while (pa < m || pb < n) {
            if (pa == m) {
                cur = B[pb++];
            } else if (pb == n) {
                cur = A[pa++];
            } else if (A[pa] < B[pb]) {
                cur = A[pa++];
            } else {
                cur = B[pb++];
            }
            sorted[pa + pb - 1] = cur;
        }
        for (int i = 0; i != m + n; ++i) {
            A[i] = sorted[i];
        }
      System.out.println(A);
    }

  public static void main(String[] args) {
    //  A = [1,2,3,0,0,0], m = 3
//  B = [2,5,6],       n = 3
    int a[] ={1,2,3,0,0,0};
    int b[] = {2,5,6};
    int m = 3;
    int n = 3;
    Solution_10_1 solution_10_1 = new Solution_10_1();
    solution_10_1.merge(a,m,b,n);
  }
}