package arithmetic.testsap;

//剑指 Offer 40. 最小的k个数
//输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
//
//   
//
//  示例 1：
//
//  输入：arr = [3,2,1], k = 2
//  输出：[1,2] 或者 [2,1]

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SolutionOffer40 {

  public int[] getLeastNumbers(int[] arr, int k) {
    int[] vec = new int[k];
    Arrays.sort(arr);
    for (int i = 0; i < k; ++i) {
      vec[i] = arr[i];
    }
    return vec;
  }

  public int[] getLeastNumbers2(int[] arr, int k) {
    int[] vec = new int[k];
    if (k == 0) { // 排除 0 的情况
      return vec;
    }
    PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
      public int compare(Integer num1, Integer num2) {
        return num2 - num1;
      }
    });
    for (int i = 0; i < k; ++i) {
      queue.offer(arr[i]);
    }
    for (int i = k; i < arr.length; ++i) {
      if (queue.peek() > arr[i]) {
        queue.poll();
        queue.offer(arr[i]);
      }
    }
    for (int i = 0; i < k; ++i) {
      vec[i] = queue.poll();
    }
    return vec;
  }



  public static void main(String[] args) {
    SolutionOffer40 solution49 = new SolutionOffer40();
    int [] nums = {1,8,6,5,4,8,3,7,2};
    int k = 2;
    System.out.println(Arrays.toString(solution49.getLeastNumbers(nums,2)));
    int [] nums2 = {1,8,6,5,4,8,3,7,2};
    System.out.println(Arrays.toString(solution49.getLeastNumbers2(nums2,2)));
  }


}
