package arithmetic.testsap.week04;

public class TestArray {
//  二维数组的概述：
//  二维数组其实就是一个元素为一维数组的数组；
//
//  格式1：
//  数据类型[][] 变量名=new 数据类型[m][n];
//  m表示这个二维数组有多少个数组
//    n表示每一个一维数组的元素个数
//  举例：
//  int[][] arr=new int[3][2];
//  定义了一个二维数组arr
//  这个二维数组有3个一维数组，名称是ar[0],arr[1],arr[2]
//  每个一维数组有2个元素，可以通过arr[m][n]来获取
//  格式2：
//  数据类型[][] 变量名=new 数据类型[m][];
//  m表示这个二维数组有多少个数组
//  这一次没有直接给出一维数组的元素个数，可以动态的给出
//  举例：
//  int[][] arr=new int[3][];
//  arr[0] = new int[2];
//  arr[1]= new int[3];
//  arr[2]=new int[1];
//  格式3：
//  数据类型[][] 变量名=new 数据类型[][]{{元素...},{元素...},{元素...}};
//  也可以是：
//  数据类型[][] 变量名={{元素...},{元素...},{元素...}};
//  举例：int[][] arr={{1,2,3},{4,6},{6}}
//
//  作者：小漫画ing
//  链接：https://www.jianshu.com/p/aa905c079332
//  来源：简书
//  著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

  /*
   * 创建一个二维数组
   * 并且遍历二维数组
   */

  public void test_1(){
    int[][] arr = {{1,2,3,11,22},{4,5,6,77,88},{7,8,9}};
    for(int x=0;x<arr.length;x++){
      for(int y=0;y<arr[x].length;y++){
        System.out.print(arr[x][y]+"   ");
      }
      System.out.println();
    }
  }


  public static void main(String[] args) {
    TestArray testArray = new TestArray();
    testArray.test_1();


  }

}
