package arithmetic.testsap.week03;

//22. 括号生成

import java.util.ArrayList;
import java.util.List;

//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
//   
//
//  示例 1：
//
//  输入：n = 3
//  输出：["((()))","(()())","(())()","()(())","()()()"]
//  示例 2：
//
//  输入：n = 1
//  输出：["()"]

//递归解法
public class Solution22 {

  private List<String> result;
  public  List<String> generateParenthesis(int n) {
    result = new ArrayList<>();
    _generate(0,0,n,"");
    return result;
  }

  private void _generate(int left, int right, int n, String s) {
    //terminator
    if(left ==n && right ==n){
      result.add(s);
      return;
    }

    // process current logic:left,right


    //drill down
    if(left < n)
      _generate(left +1,right,n,s +"(");

    if(left > right)
      _generate(left,right+1,n,s+")");

  }

  public static void main(String[] args) {
    Solution22 solution22 = new Solution22();
    int n = 3;
    Solution22 solution221 = new Solution22();
    System.out.println(solution221.generateParenthesis(n));
  }


}
