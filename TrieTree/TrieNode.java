package com.duke.trietree;

public class TrieNode {
    TrieNode[] trieNode;
    boolean isEndOfWord;
    TrieNode()
    {
        trieNode = new TrieNode[26];
        isEndOfWord=false;
    }
}
