package arithmetic.testsap.week03;

public class BinarySearch {

  public int binarySearch(int[] array, int target) {
    int left = 0, right = array.length - 1, mid;
    while (left <= right) {
      mid = (right - left) / 2 + left;
      if (array[mid] == target) {
        return mid;
      } else if (array[mid] > target) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    BinarySearch binarySearch = new BinarySearch();

    int [] nums = {10,14,18,33,35,37,44};
    System.out.println(binarySearch.binarySearch(nums,37));

  }


}
