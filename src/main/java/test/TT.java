package test;

public class TT {

	 public static void main(String[] args) {
	  // 整数类型数组（原始数据类型）,随便定义
	  int[] numbs = { 87, 25, 4, 22, 2, 56, 11, 28, 35, 15 };

	  // 定义一个临时交换变量
	  int temp = 0;
	  for (int i = 0; i < numbs.length-1; i++) {
	   for (int j = 0; j < numbs.length ; j++) {
	    if (numbs[i] > numbs[j]) {
	     temp = numbs[i];
	     numbs[i] = numbs[j];
	     numbs[j] = temp;
	    }
	    
	   }
	  }

	  // 打印冒泡排序过后的数组
	  for (int i = 0; i < numbs.length; i++) {
	   System.out.println(numbs[i]);
	  }
	 }

	}
