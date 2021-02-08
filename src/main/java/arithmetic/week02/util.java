package arithmetic.week02;

import java.util.LinkedList;
import java.util.Queue;

public class util {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	/*
	 * construct TreeNode from a array format string, for test cases of LeetCode
	 */
	public static TreeNode createTree(String tree) {
		// {1,2,3,4,#,#,#,5,#,6,#,7,#,8}
		String[] ss = tree.split(",");
		return createTree(ss);
	}

	public static TreeNode createTree(String[] tree) {
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		// 1st one should not be #
		TreeNode root = constructOne(tree[0]);
		q.add(root);
		int idx = 1;
		while (!q.isEmpty()) {
			
			TreeNode tn = q.poll();
			if (tn == null) {
				continue;
			}
			// construct tn's left&right node
			// when to stop
			if (idx == tree.length) {
				break;
			}
			TreeNode left_ = constructOne(tree[idx]);
			tn.left = left_;
			q.add(left_);
			idx++;
			if (idx == tree.length) {
				break;
			}
			TreeNode right_ = constructOne(tree[idx]);
			idx++;
			
			
			tn.right = right_;
			// add to queue
			q.add(right_);
		}

		return root;

	}

	private static void printNode(TreeNode tn, int indent) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < indent; i++) {
			sb.append("	");
		}
		sb.append(tn.val);
		System.out.println(sb.toString());
	}

	public static void printTree(TreeNode root, int indent) {
		if (root == null) {
			return;
		}
//		if (root.left == null && root.right == null) {
//			printNode(root, indent);
//		}
		// right
		printTree(root.right, indent + 1);
		// self
		printNode(root, indent);
		// left
		printTree(root.left, indent + 1);
	}

	public static void printTree(TreeNode root) {
		// right first
		printTree(root, 0);
	}

	private static TreeNode constructOne(String s) {
		if (s.compareTo("#") == 0) {
			return null;
		} else {
			return new TreeNode(Integer.parseInt(s));
		}
	}

	public static void main(String args[]) {
		TreeNode tn = createTree("2,#,3,#,4,#,5,#,6");
		printTree(tn);
	}
}