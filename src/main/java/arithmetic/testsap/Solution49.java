package arithmetic.testsap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//49. 字母异位词分组
//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
//
//  示例:
//
//  输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//  输出:
//  [
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//  ]
public class Solution49 {

  public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<String, List<String>>();
    for (String str : strs) {
      char[] array = str.toCharArray();
      Arrays.sort(array);
      String key = new String(array);
      List<String> list = map.getOrDefault(key, new ArrayList<String>());
      list.add(str);
      map.put(key, list);
    }
    return new ArrayList<List<String>>(map.values());
  }

  public static void main(String[] args) {
    Solution49 solution49 = new Solution49();
    String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
    System.out.println(solution49.groupAnagrams(strs));
  }


}
