package arithmetic.week06;



//709. 转换成小写字母

//实现函数 ToLowerCase()，该函数接收一个字符串参数 str，并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。
//
//   
//
//  示例 1：
//
//  输入: "Hello"
//  输出: "hello"
//
//  来源：力扣（LeetCode）
//  链接：https://leetcode-cn.com/problems/to-lower-case
//  著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution709 {

    public String toLowerCase1(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : str.toCharArray()) {
            // a-z：97-122  A-Z：65-90  0-9：48-57
            if (ch >= 'A' && ch <= 'Z') {
                sb.append((char)(ch + 32));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }


    public String toLowerCase2(String str) {
      if (str == null || str.length() == 0) {
        return str;
      }
      char[] ch = str.toCharArray();
      for (int i = 0; i < str.length(); i++) {
        if (ch[i] >= 'A' && ch[i] <= 'Z') {
          ch[i] += 32;
        }
      }
      return String.valueOf(ch);
    }


//  用位运算的技巧：
//
//  大写变小写、小写变大写：字符 ^= 32;
//  大写变小写、小写变小写：字符 |= 32;
//  大写变大写、小写变大写：字符 &= 33;
//  SCII码表中大写的A是65，小写的a是97，它们的差是32
//65 | 32 转为二进制（按8位来算）可以得到 0100 0001 | 0010 0000 = 0110 0001 = 97 = a

  public String toLowerCase3(String str) {
    if (str == null || str.length() == 0) {
      return str;
    }
    char[] ch = str.toCharArray();
    for (int i = 0; i < str.length(); i++) {
      ch[i] |= 32;
    }
    return String.valueOf(ch);
  }



  public static void main(String[] args) {
    Solution709 solution709 = new Solution709();
    System.out.println(solution709.toLowerCase1("Hello9"));
    System.out.println(solution709.toLowerCase2("Hello9"));
    System.out.println(solution709.toLowerCase3("Hello9"));
  }

}

