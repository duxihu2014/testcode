package arithmetic.week06.sort;

import java.util.Arrays;

public class Sorts {

  //1.冒泡排序
  public void dubbleSort(int[] arr){
    int length = arr.length;
    for (int i = 0; i < length - 1; i++) {
      for (int j = 0; j < length - 1 - i; j++) {
          if(arr[i] > arr[j+1]){
            int tmp = arr[j+1];
            arr[j+1] = arr[i];
            arr[i] = tmp;
          }
      }
    }
  }

  //2.选择排序
  public void selectionSort(int[] arr){
    int length = arr.length;
    int minIndex;
    int temp;
    for (int i = 0; i < length - 1; i++) {
      minIndex = i;
      for (int j = i + 1; j < length; j++) {
          if(arr[j]<arr[minIndex]){
            minIndex = j;
          }
      }
      //交换数据
      temp = arr[i];
      arr[i] = arr[minIndex];
      arr[minIndex] = temp;
    }

  }

  //3.插入排序
  public void insertSort(int[] arr){
    int len = arr.length;
    int preIndex;
    int current;
    for (int i = 1; i < len; i++) {
      preIndex = i-1;
      current = arr[i];
      while (preIndex >= 0 && arr[preIndex] > current){
        arr[preIndex + 1] = arr[preIndex];
        preIndex--;
      }
      arr[preIndex + 1] = current;
    }
  }

  public static void main(String[] args) {
    Sorts sorts = new Sorts();
    int[] arrs = {1,3,5,4,9,34,8,27,12};
    sorts.insertSort(arrs);
    System.out.println(Arrays.toString(arrs));
  }



}
