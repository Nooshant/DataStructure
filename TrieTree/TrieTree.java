package com.duke.trietree;

import java.util.Stack;

public class TrieTree {
	private TrieNode trieRoot = null;

	TrieTree() {
		trieRoot = new TrieNode();
	}

	public void insert(String key) {
		TrieNode node = trieRoot;
		if (search(key)) {
			return;
		}
		for (int i = 0; i < key.length(); i++) {
			int index = key.charAt(i) - 'a';
			if (node.trieNode[index] == null) {
				node.trieNode[index] = new TrieNode();
			}
			node = node.trieNode[index];
		}
		node.isEndOfWord = true;
	}

	public boolean search(String key) {
		TrieNode node = trieRoot;
		// apple
		for (int i = 0; i < key.length(); i++) {
			int index = key.charAt(i) - 'a';
			if (node.trieNode[index] == null) {
				System.out.println("\"" + key + "\"" + " not found!");
				return false;
			}
			node = node.trieNode[index];
		}
		if (node != null && node.isEndOfWord) {
			System.out.println("\"" + key + "\"" + " found");
			return true;
		}
		System.out.println("\"" + key + "\"" + " not found!");
		return false;
	}

	public void delete(String key) {
		if (search(key)) {
			Stack<TrieNode> st = new Stack<>();
			//nodeIndex array store the index of character in each TrieNode
			int[] nodeIndex = new int[key.length()];
			TrieNode node = trieRoot;
			for (int i = 0; i < key.length(); i++) {
				int index = key.charAt(i) - 'a';
				if (node.trieNode[index] != null) {
					//push all the character TrieNode of key into stack
					st.push(node.trieNode[index]);
					nodeIndex[i] = index;
				}
				node = node.trieNode[index];
			}

			boolean isTop = true;
			int len = key.length() - 1;
			while (!st.isEmpty()) {
				TrieNode popNode = st.pop();
				if (isTop) {
					//since last character TrieNode indicate the word end so make it false.
					popNode.isEndOfWord = false;
					isTop = false;
				}
				//If node has child node means next character node is part of some other existing word
				// so don't delete it
				if (!hasChildren(popNode)) {
					//this node doesn't have child node
					//so set this node particular index to null
					//deepak and deepka both are present and now trying to delete deepka
					// so till deep is common and is also a part of another word.
					// so delete only 'ka' from tree. and here nodeIndex[len] tell the 'a' and 'k' character node in TrieNode 
					popNode.trieNode[nodeIndex[len]] = null;
					len--;
				}
			}
			System.out.println("\"" + key + "\"" + " deleted");
		}
	}

	private boolean hasChildren(TrieNode popNode) {
		if (popNode != null) {
			for (int i = 0; i < 26; i++) {
				if (popNode.trieNode[i] != null) {
					return true;
				}
			}
		}
		return false;
	}
}
