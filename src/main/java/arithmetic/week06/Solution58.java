package arithmetic.week06;


//58. 最后一个单词的长度
//  给你一个字符串 s，由若干单词组成，单词之间用空格隔开。返回字符串中最后一个单词的长度。如果不存在最后一个单词，请返回 0 。
//
//  单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
//
//   
//
//  示例 1：
//
//  输入：s = "Hello World"
//  输出：5
//  示例 2：
//
//  输入：s = " "
//  输出：0
public class Solution58 {
    public int lengthOfLastWord(String s) {
        int end = s.length() - 1;
        while(end >= 0 && s.charAt(end) == ' ') end--;
      if (end < 0) {
        return 0;
      }
        int start = end;
        while(start >= 0 && s.charAt(start) != ' ') start--;
        return end - start;
    }

  public static void main(String[] args) {

    Solution58 solution58 = new Solution58();
    System.out.println(solution58.lengthOfLastWord("Hello World "));

  }
}

