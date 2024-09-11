import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Tree {
   static class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;

       TreeNode() {
       }

       TreeNode(int val) {
           this.val = val;
       }

       TreeNode(int val, TreeNode left, TreeNode right) {
           this.val = val;
           this.left = left;
           this.right = right;
       }
   }

    /**
     * 二叉树的中序遍历  递归遍历 左 中 右子树
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        inorder(root, res);
        return res;
    }

    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    /**
     * 层序遍历
     * @param
     */

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int currentLevelSize = queue.size();
            for (int i = 1; i <= currentLevelSize; ++i) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ret.add(level);
        }

        return ret;
    }


    public static void main(String[] args) {
        Tree tree = new Tree();
        TreeNode root = new TreeNode(5);
        TreeNode left = new TreeNode(10);
        TreeNode right = new TreeNode(20);
        root.left = left;
        root.right = right;
        System.out.println(tree.inorderTraversal(root));
    }

}
