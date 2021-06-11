package com.duke.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {

	private TreeNode root;

	BinaryTree() {
		root = null;
	}

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
	
	
	public void display()
	{
		Queue<TreeNode> queue = new java.util.LinkedList<TreeNode>();
		queue.add(root);
		
		System.out.println("Display Binary tree:");
		while(!queue.isEmpty())
		{
			TreeNode node = queue.poll();
			if(node.getLeft()!=null)
			{
				queue.add(node.getLeft());
			}
			 if(node.getRight()!=null)
			{
				queue.add(node.getRight());
			}
			System.out.print(node.getData()+" ");
		}
	}
	
	
	public void inorder()
	{
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
	
	public void preorder()
	{
		System.out.println("\nPreOrder traversal:");
		Stack<TreeNode> st = new Stack<>();
		st.push(root);
		while (!st.isEmpty()) {

			TreeNode cur = st.pop();
			System.out.print(cur.getData()+" ");

			if (cur.getRight() != null) {
				st.push(cur.getRight());
			}

			if (cur.getLeft() != null) {
				st.push(cur.getLeft());
			}
		}
	}
	
	public void levelOrder()
	{
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

	public void postorder()
	{
		System.out.println("\nPostOrder traversal:");
		Stack<TreeNode> st = new Stack<>();
		st.push(root);
		TreeNode popped = null;
		while(!st.empty())
		{
			TreeNode node = st.peek();
			while(node.getLeft()!=null && node.getLeft()!= popped)
			{
				node= node.getLeft();
				st.push(node);
			}
			
			if(node.getRight()==null)
			{
				popped = st.pop();
				System.out.print(popped.getData()+" ");
				while(!st.isEmpty() && st.peek().getRight()== popped)
				{
					popped = st.pop();
					System.out.print(popped.getData()+" ");
				}
			}
			else {
				if(node.getRight()!=null && node.getRight()!= popped)
				{
					st.push(node.getRight());
				}
			}
		}
	}

	public void contains(int data)
	{
		TreeNode node = root;
		while(node!=null)
		{
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

	public void delete(int data)
	{
		System.out.println("\nDelete node:"+data);
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
				}
				else if (cur.getData() < data) {
					prev = cur;
					cur = cur.getRight();
				} else {
					prev = cur;
					cur = cur.getLeft();
				}
			}

			System.out.println("Prev Node: "+prev.getData());
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

	private TreeNode getSuccessorNode(TreeNode cur) {
		TreeNode temp = null;
		if (cur.getRight() != null) {
			temp = cur;
			cur = cur.getRight();
		}
		if(cur.getLeft() == null)
		{
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
	
}
