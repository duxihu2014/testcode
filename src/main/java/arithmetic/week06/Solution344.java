package arithmetic.week06;



//344. 反转字符串

import java.lang.reflect.Array;
import java.util.Arrays;

//编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
//
//  不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
//
//  你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
//
//   
//
//  示例 1：
//
//  输入：["h","e","l","l","o"]
//  输出：["o","l","l","e","h"]
//  示例 2：
//
//  输入：["H","a","n","n","a","h"]
//  输出：["h","a","n","n","a","H"]
//
//  来源：力扣（LeetCode）
//  链接：https://leetcode-cn.com/problems/reverse-string
//  著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution344 {


  public void reverseString(char[] s) {
    int n = s.length;
    for (int left = 0, right = n - 1; left < right; ++left, --right) {
      char tmp = s[left];
      s[left] = s[right];
      s[right] = tmp;
    }
  }

  public static void main(String[] args) {
     char[] s = {'h','i','j','k','l','m'};
    Solution344 solution344 = new Solution344();
    solution344.reverseString(s);
    for (int i = 0; i < s.length; i++) {
      System.out.println(s[i]);
    }

  }

}

//作者：LeetCode-Solution
//  链接：https://leetcode-cn.com/problems/reverse-string/solution/fan-zhuan-zi-fu-chuan-by-leetcode-solution/
//  来源：力扣（LeetCode）
//  著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。