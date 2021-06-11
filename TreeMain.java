package com.duke.tree;

public class TreeMain {

	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();
		bt.add(15);
		bt.add(5);
		bt.add(35);
		bt.add(45);
		bt.add(20);
		bt.add(10);
		bt.add(4);
		bt.add(3);
		bt.display();
		bt.inorder();
		bt.preorder();
		bt.levelOrder();
		//bt.display();
		//bt.delete(15);
		//bt.delete(4);
		//bt.delete(35);
		//bt.display();
		bt.postorder();
	}

}
