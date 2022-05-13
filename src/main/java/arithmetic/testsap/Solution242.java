package arithmetic.testsap;


//242. 有效的字母异位词
//  给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
//
//  示例 1:
//
//  输入: s = "anagram", t = "nagaram"
//  输出: true
//  示例 2:
//
//  输入: s = "rat", t = "car"
//  输出: false
class Solution242 {

  public boolean isAnagram(String s, String t) {
    if(s.length() != t.length())
      return false;
    int[] alpha = new int[26];
    for(int i = 0; i< s.length(); i++) {
      alpha[s.charAt(i) - 'a'] ++;
      alpha[t.charAt(i) - 'a'] --;
    }
    for(int i=0;i<26;i++)
      if(alpha[i] != 0)
        return false;
    return true;
  }

  public static void main(String[] args) {
    Solution242 solution242 = new Solution242();
    String s = "anagram";
    String t = "nagaram";
    solution242.isAnagram(s,t);
  }

}