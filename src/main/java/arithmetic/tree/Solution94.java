package arithmetic.tree;

import java.util.ArrayList;
import java.util.List;

//144. 二叉树的中序遍历
//  给你二叉树的根节点 root ，返回它节点值的 中序 遍历。
//
//  输入：root = [1,null,2,3]
//  输出：[1,2,3]
public class Solution94 {

  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    preorder(root, res);
    return res;
  }

  public void preorder(TreeNode root, List<Integer> res) {
    if (root == null) {
      return;
    }

    preorder(root.left, res);
    res.add(root.val);
    preorder(root.right, res);

  }

}