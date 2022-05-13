package arithmetic.testsap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//239. 滑动窗口最大值
//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
//
//  返回滑动窗口中的最大值。
//
//   
//
//  示例 1：
//
//  输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//  输出：[3,3,5,5,6,7]
//  解释：
//  滑动窗口的位置                最大值
//  ---------------               -----
//  [1  3  -1] -3  5  3  6  7      3
//  1 [3  -1  -3] 5  3  6  7       3
//  1  3 [-1  -3  5] 3  6  7       5
//  1  3  -1 [-3  5  3] 6  7       5
//  1  3  -1  -3 [5  3  6] 7       6
//  1  3  -1  -3  5 [3  6  7]      7
//  示例 2：
//
//  输入：nums = [1], k = 1
//  输出：[1]
//  示例 3：
//
//  输入：nums = [1,-1], k = 1
//  输出：[1,-1]
//  示例 4：
//
//  输入：nums = [9,11], k = 2
//  输出：[11]
//  示例 5：
//
//  输入：nums = [4,-2], k = 2
//  输出：[4]
class Solution239 {

  public static int[] maxSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
      public int compare(int[] pair1, int[] pair2) {
        return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
      }
    });
    for (int i = 0; i < k; ++i) {
      pq.offer(new int[]{nums[i], i});
    }
    int[] ans = new int[n - k + 1];
    ans[0] = pq.peek()[0];
    for (int i = k; i < n; ++i) {
      pq.offer(new int[]{nums[i], i});
      while (pq.peek()[1] <= i - k) {
        pq.poll();
      }
      ans[i - k + 1] = pq.peek()[0];
    }
    return ans;
  }

  public static void main(String[] args) {
    int [] nums = {1,3,-1,-3,5,3,6,7};
    int k = 3;
    System.out.println(Arrays.toString(Solution239.maxSlidingWindow(nums,k)));
  }
}