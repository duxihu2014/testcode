package arithmetic;

import java.util.ArrayList;
import java.util.List;

//22. 括号生成
//https://leetcode-cn.com/problems/generate-parentheses/
public class Solution22 {
  ArrayList<String> strings = null;
  public List<String> generateParenthesis(int n) {
    strings = new ArrayList<String>();
    generateParenthesis(n,0,0,"");
    return strings;
  }

  public void generateParenthesis(int n, int left, int right, String s){
    if(left == n && right == n){
      strings.add(s);
      return;
    }
    if(left<n) generateParenthesis(n,left+1,right,s+"(");
    if(right<left) generateParenthesis(n,left,right+1,s+")");
  }

  public static void main(String[] args) {
    Solution22 solution = new Solution22();
    System.out.println(solution.generateParenthesis(3));
  }
}