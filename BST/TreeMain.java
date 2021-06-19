package com.duke.tree;

public class TreeMain {

	public static void main(String[] args) {
	BinarySearchTree bt = new BinarySearchTree();
		bt.add(15);
		bt.add(5);
		bt.add(35);
		bt.add(45);
		bt.add(20);
		bt.add(10);
		bt.add(4);
		bt.add(3);
		bt.display();
		//bt.inorder();
		//bt.preorder();
		//bt.levelOrder();
		//bt.display();
		//bt.delete(15);
		//bt.delete(4);
		//bt.delete(35);
		//bt.display();
		//bt.postorder();
		//bt.kthSmallestElementInBST(5);
		//bt.maxDepthOfBST();
		//bt.maxPathSum();
		//bt.zigZagTraversal();
		
//		int sortedArr[]= {3,4,5,10,15,20,35,45};
//		bt.sortedArrayToBST(sortedArr);
		//bt.isBST();
		//bt.lowestCommonAncestor(3,45);
		
		
		BinarySearchTree btree = new BinarySearchTree();
		int[] inorder = {3, 4, 5, 10, 15, 20, 35, 45};
		int[] preorder = {15, 5, 4, 3, 10, 35, 20, 45};
		btree.buildTreeFomInorderNpreorderArray(preorder, inorder);
		btree.display();
		btree.printRootToLeafPath();
		btree.printRootToLeafPathSum(30);
	}

}
