package com.duke.tree;

import java.util.*;

/**
 * @author thakurde
 *
 */
public class BinarySearchTree {

	private TreeNode root;

	BinarySearchTree() {
		root = null;
	}

	//method to add node in BST
	public void add(int data) {
		TreeNode node = new TreeNode(data);
		if (root == null) {
			root = node;
			return;
		}

		TreeNode temp = root;
		TreeNode prevL = null;
		TreeNode prevR = null;

		while (temp != null) {
			if (data <= temp.getData()) {
				prevL = temp;
				temp = temp.getLeft();
				if (temp == null) {
					prevL.setLeft(node);
				}
			} else if (data > temp.getData()) {
				prevR = temp;
				temp = temp.getRight();
				if (temp == null) {
					prevR.setRight(node);
				}
			}
		}
	}

	//Display all the node of binary tree
	public void display() {
		Queue<TreeNode> queue = new java.util.LinkedList<TreeNode>();
		queue.add(root);

		System.out.println("\nDisplay Binary tree:");
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if (node.getLeft() != null) {
				queue.add(node.getLeft());
			}
			if (node.getRight() != null) {
				queue.add(node.getRight());
			}
			System.out.print(node.getData() + " ");
		}
	}

	//Inorder traversal of binary tree
	public void inorder() {
		System.out.println("\nInOrder traversal:");
		Stack<TreeNode> st = new Stack<>();

		TreeNode cur = root;
		while (cur != null || !st.isEmpty()) {

			while (cur != null) {
				st.push(cur);
				cur = cur.getLeft();
			}

			cur = st.pop();

			System.out.print(cur.getData() + " ");
			cur = cur.getRight();
		}
	}

	//Pre-Order traversal of BT
	public void preorder() {
		System.out.println("\nPreOrder traversal:");
		Stack<TreeNode> st = new Stack<>();
		st.push(root);
		while (!st.isEmpty()) {

			TreeNode cur = st.pop();
			System.out.print(cur.getData() + " ");

			if (cur.getRight() != null) {
				st.push(cur.getRight());
			}

			if (cur.getLeft() != null) {
				st.push(cur.getLeft());
			}
		}
	}

	//Level order or Breadth first search
	public void levelOrder() {
		System.out.println("\nLevelOrder traversal:");
		Queue<TreeNode> queue = new LinkedList<TreeNode>();

		queue.add(root);

		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			System.out.print(node.getData() + " ");

			if (node.getLeft() != null) {
				queue.add(node.getLeft());
			}

			if (node.getRight() != null) {
				queue.add(node.getRight());
			}
		}
	}

	//post-order traversal of Binary tree
	public void postorder() {
		System.out.println("\nPostOrder traversal:");
		Stack<TreeNode> st = new Stack<>();
		st.push(root);
		TreeNode popped = null;
		while (!st.empty()) {
			TreeNode node = st.peek();
			while (node.getLeft() != null && node.getLeft() != popped) {
				node = node.getLeft();
				st.push(node);
			}

			if (node.getRight() == null) {
				popped = st.pop();
				System.out.print(popped.getData() + " ");
				while (!st.isEmpty() && st.peek().getRight() == popped) {
					popped = st.pop();
					System.out.print(popped.getData() + " ");
				}
			} else {
				if (node.getRight() != null && node.getRight() != popped) {
					st.push(node.getRight());
				}
			}
		}
	}

	//method to check the particular node
	public void contains(int data) {
		TreeNode node = root;
		while (node != null) {
			if (node.getData() == data) {
				System.out.println("Found");
				return;
			}
			if (node.getData() > data) {
				node = node.getRight();
			} else {
				node = node.getLeft();
			}
		}
	}

	//delete any node from binary tree
	public void delete(int data) {
		System.out.println("\nDelete node:" + data);
		TreeNode cur = root;
		TreeNode prev = null;
		TreeNode left = null;
		TreeNode right = null;

		// case -3 remove root, find the lowest successor
		if (cur.getData() == data) {
			left = cur.getLeft();
			right = cur.getRight();
			TreeNode lowestNode = getSuccessorNode(cur);
			lowestNode.setLeft(left);
			lowestNode.setRight(right);
			root = lowestNode;
		} else {
			while (cur != null) {
				if (cur.getData() == data) {
					break;
				} else if (cur.getData() < data) {
					prev = cur;
					cur = cur.getRight();
				} else {
					prev = cur;
					cur = cur.getLeft();
				}
			}

			System.out.println("Prev Node: " + prev.getData());
			// case-1 single child node
			if (cur.getLeft() == null || cur.getRight() == null) {
				if (prev.getLeft().getData() == cur.getData()) {
					prev.setLeft(null);
				} else if (prev.getRight().getData() == cur.getData()) {
					prev.setRight(null);
				}
			}
			// case-2 single child node
			else if (cur.getLeft() != null && cur.getRight() != null) {
				TreeNode node = cur;
				TreeNode lowestNode = getSuccessorNode(cur);
				if (prev.getLeft().getData() == node.getData()) {
					prev.setLeft(lowestNode);
				} else if (prev.getRight().getData() == node.getData()) {
					prev.setRight(lowestNode);
				}
				lowestNode.setLeft(node.getLeft());
				lowestNode.setRight(node.getRight());
			}
		}
	}
	
	// Kth smallest element of a binary tree
	public void kthSmallestElementInBST(int k)
	{
		int num=k;
		Stack<TreeNode> st = new Stack<>();
		TreeNode cur = root;
		while (cur != null || !st.isEmpty()) {
			while (cur != null) {
				st.push(cur);
				cur = cur.getLeft();
			}
			cur = st.pop();
			k--;
			if(k==0)
			{
				System.out.print("\n" + num + "th smallest Element: " + cur.getData());
				return;
			}
			cur = cur.getRight();
		}
	}

	//get the successor node of any particular
	private TreeNode getSuccessorNode(TreeNode cur) {
		TreeNode temp = null;
		if (cur.getRight() != null) {
			temp = cur;
			cur = cur.getRight();
		}
		if (cur.getLeft() == null) {
			temp.setRight(null);
			return cur;
		}
		while (cur.getLeft() != null) {
			temp = cur;
			cur = cur.getLeft();
		}
		temp.setLeft(null);
		return cur;
	}

	//maximum depth of BST
	public void maxDepthOfBST()
	{
		int maxDepth = maxDepthOfBST(root);
		System.out.println("\nMaximum Depth:" + maxDepth + "\n");
	}

	private int maxDepthOfBST(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int lDepth = maxDepthOfBST(node.getLeft());
		int rDepth = maxDepthOfBST(node.getRight());

		if (lDepth > rDepth) {
			return lDepth + 1;
		} else {
			return rDepth + 1;
		}
	}

	//Maximum path sum
	public void maxPathSum() {
		Res result = new Res();
		result.val = Integer.MIN_VALUE;
		System.out.println();
		maxPathSum(root, result);
		System.out.println("Maximum Path sum:" + result.val);
	}

	private int maxPathSum(TreeNode node, Res res) {
		if (node == null) {
			return 0;
		}
		int lSum = maxPathSum(node.getLeft(), res);
		int rSum = maxPathSum(node.getRight(), res);

		int temp = Math.max(Math.max(lSum, rSum) + node.getData(), node.getData());
		int finalSum = Math.max(temp, lSum + rSum + node.getData());
		res.val = Math.max(finalSum, res.val);
		//System.out.println("Res..."+res);
		return temp;
	}

	// Method to check for two binary tree is symmetric or not
/* Example of symmetric tree
		1
	   /   \
	  2     2
	 / \   / \
	3   4 4   3
*/
	public void isSymmetricTree(TreeNode node1, TreeNode node2)
	{
		boolean isSymmetric = isSymmetric(node1, node2);
		System.out.println("isSymmetric tree:"+isSymmetric);
	}

	private boolean isSymmetric(TreeNode node1, TreeNode node2) {
		if (node1 == null && node2 == null) {
			return true;
		}

		if (node1 != null && node2 != null && node1.data == node2.data) {
			return (isSymmetric(node1.left, node2.right) && isSymmetric(node1.right, node2.left));
			}
		return false;
	}

	//Binary Tree Zigzag Level Order Traversal(level-1 from left, level-2 from right,level-3 from left ...)
	public void zigZagTraversal()
	{
		Stack<TreeNode> left2Right = new Stack<>();
		Stack<TreeNode> right2Left = new Stack<>();
		if(root != null)
		{
			left2Right.push(root);
		}
		boolean fromRight = true;
		boolean fromLeft = false;
		TreeNode node = null;
		while(!left2Right.isEmpty() || !right2Left.isEmpty())
		{
			if (fromRight) {
				node = left2Right.pop();
				System.out.println(node.data + " ");
				if (node.left != null) {
					right2Left.push(node.left);
				}
				if (node.right != null) {
					right2Left.push(node.right);
				}

				if (left2Right.isEmpty()) {
					fromLeft = true;
					fromRight = false;
				}
			} else if (fromLeft) {
				node = right2Left.pop();
				System.out.println(node.data + " ");
				if (node.right != null) {
					left2Right.add(node.right);
				}
				if (node.left != null) {
					left2Right.add(node.left);
				}
				if (right2Left.isEmpty()) {
					fromLeft = false;
					fromRight = true;
				}
			}
		}
	}
	
	public void sortedArrayToBST(int arr[])
	{
		root = sortedArrayToBstHelper(arr,0,arr.length-1);
		inorder();
	}

	private TreeNode sortedArrayToBstHelper(int[] arr, int start, int end)
	{
		if(start > end)
		{
			return null;
		}
		int mid = (start + end)/2;
		TreeNode node = new TreeNode(arr[mid]);
		node.left = sortedArrayToBstHelper(arr,start,mid-1);
		node.right = sortedArrayToBstHelper(arr,mid+1,end);
		return node;
	}
	
	// check if a tree is BST or not
	public void isBST()
	{
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		
		TreeNode node =null;
		while(!q.isEmpty())
		{
			node = q.remove();
			if (node.left != null) {
				if (node.data > node.left.data) {
					q.add(node.left);
				} else {
					System.out.println("\nNot a BST");
					return;
				}
			}

			if (node.right != null) {
				if (node.right.data > node.data) {
					q.add(node.right);
				} else {
					System.out.println("\nNot a BST");
					return;
				}
			}
		}
		System.out.println("\nIt is a BST");
	}

	//method to find the lowest common ancestor
	public void lowestCommonAncestor(int val1,int val2)
	{
		TreeNode node = lowestCommonAncestorRecursive(root,val1,val2);
		System.out.println("Recursion Lowest Common Ancestor: "+node.getData());
		node = lowestCommonAncestorIterative(val1,val2);
		System.out.println("Iterative Lowest Common Ancestor: "+node.getData());
	}

	private TreeNode lowestCommonAncestorRecursive(TreeNode node, int val1, int val2) {
		if (node == null) {
			return null;
		}
		if (node.data == val1 || node.data == val2) {
			return node;
		}
		TreeNode leftNode = lowestCommonAncestorRecursive(node.left, val1, val2);
		TreeNode rightNode = lowestCommonAncestorRecursive(node.right, val1, val2);

		if(leftNode!=null && rightNode!=null)
		{
			return node;
		}

		if (leftNode != null) {
			return leftNode;
		}
		return rightNode;
	}

	private TreeNode lowestCommonAncestorIterative(int val1, int val2)
	{
		List<TreeNode> list1 = new ArrayList<>();
		List<TreeNode> list2 = new ArrayList<>();
		findPathOfNode(val1,list1);
		findPathOfNode(val2,list2);
		int len1 = list1.size();
		int len2 = list2.size();
		for(int i=0;i<len1;i++)
			if (i<len2 && list1.get(i) == list2.get(i)) {
				return list1.get(i);
			}
		return null;
	}

	private void findPathOfNode(int val, List<TreeNode> list) {
		TreeNode node = null;
		Stack<TreeNode> st = new Stack<>();
		st.push(root);
		while(!st.isEmpty()) {
			node = st.pop();
			list.add(node);
			if (node.data == val) {
				return;
			} else if (node.data > val) {
				node = node.left;
			} else if (node.data < val) {
				node = node.right;
			}
		}
	}
	
	// Build Binary search Tree from Preorder and inorder array
	public void buildTreeFomInorderNpreorderArray(int[] preorder, int[] inorder)
	{
		Set<TreeNode> set = new HashSet<>();
		Stack<TreeNode> stack = new Stack<>();
		
		for (int pre = 0, in = 0; pre < preorder.length;)
		{
			TreeNode node = null;
			do {
				node = new TreeNode(preorder[pre]);
				if (root == null) {
					root = node;
				}
				if (!stack.isEmpty()) {
					if (set.contains(stack.peek())) {
						set.remove(stack.peek());
						stack.pop().right = node;
					} else {
						stack.peek().left = node;
					}
				}
				stack.push(node);
			}
			while(preorder[pre++] != inorder[in] && pre < preorder.length);
			node = null;
			while (!stack.isEmpty() && in < inorder.length && stack.peek().data == inorder[in]) {
				node = stack.pop();
				in++;
			}
			if (node != null) {
				set.add(node);
				stack.push(node);
			}
		}
	}
	
	// print all path from root to leaf
	public void printRootToLeafPath()
	{
		System.out.println("Root to leaf path:");
		Deque<TreeNode> deque = new LinkedList<>();
		TreeNode node = root;
		rootToLeafPathHelper(node, deque);
	}

	private void rootToLeafPathHelper(TreeNode node, Deque<TreeNode> deque) {
		
		if(node == null)
		{
			return ;
		}
		deque.addLast(node);
		if(node.left==null && node.right==null)
		{
			print(deque);
		}
		rootToLeafPathHelper(node.left, deque);
		rootToLeafPathHelper(node.right, deque);
		deque.removeLast();
	}

	private void print(Queue<TreeNode> queue) {
		queue.stream().forEach(q->{
			System.out.print(q.data+" ");
		});
		System.out.println();
	}

	// print path Sum from root to leaf
	public void printRootToLeafPathSum(int sum) {
		System.out.println("Root to leaf path Sum:");
		Deque<TreeNode> deque = new LinkedList<>();
		Stack<Integer> stack = new Stack<>();
		stack.add(0);
		TreeNode node = root;
		rootToLeafPathSumHelper(node, deque, sum, stack);
	}

	private void rootToLeafPathSumHelper(TreeNode node, Deque<TreeNode> deque, int sum, Stack<Integer> stack) {
		if (node == null) {
			return;
		}
		int sum_so_far = node.data;
		stack.push(stack.peek() + sum_so_far);
		deque.add(node);
		if (node.left == null && node.right == null && (stack.peek() == sum)) {
			print(deque);
		}
		rootToLeafPathSumHelper(node.left, deque, sum, stack);
		rootToLeafPathSumHelper(node.right, deque, sum, stack);
		deque.removeLast();
		stack.pop();
	}
	
	
	
	
	
	
	
	
	
	
	/*
	 * Iterative solution for maximum path sum
	 * 
	 Map<TreeNode,Integer> sum=new HashMap<>();
        sum.put(null,0);
        Stack<TreeNode> stack=new Stack<>();
        int res=Integer.MIN_VALUE;
        while(root!=null||!stack.isEmpty()){
            while(root!=null){
                if(root.right!=null)
                    stack.push(root.right);
                stack.push(root);
                root=root.left;
            }
            TreeNode cur=stack.pop();
            if(!stack.isEmpty()&&stack.peek()==cur.right){
                stack.pop();
                stack.push(cur);
                root=cur.right;
            } else {
                int left=Math.max(0,sum.get(cur.left));
                int right=Math.max(0,sum.get(cur.right));
                res=Math.max(res,left+right+cur.val);
                sum.put(cur,Math.max(left,right)+cur.val);
                root=null;
            }
        }
        return res;
	 */
}

class Res{
	public int val;
}
