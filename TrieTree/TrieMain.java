package com.duke.trietree;

public class TrieMain {
    public static void main(String[] args) {
        TrieTree trie = new TrieTree();
        trie.insert("deepak");
        trie.search("apple");
        trie.search("deepak");
        trie.insert("deepka");
        trie.delete("deepka");
        trie.search("deepka");
        trie.search("deepak");
        trie.delete("aaple");
        trie.delete("deep");
    }
}
