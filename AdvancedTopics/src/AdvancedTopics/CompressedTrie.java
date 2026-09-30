package AdvancedTopics;

import java.util.HashMap;
import java.util.Map;

public class CompressedTrie {

    private static class Node {
        String label;
        boolean isWord;
        Map<Character, Node> children = new HashMap<>();

        Node(String label) {
            this.label = label;
        }
    }

    private final Node root = new Node("");

    public void insert(String word) {

        if (word == null || word.isEmpty())
            return;

        Node current = root;

        while (!word.isEmpty()) {

            char first = word.charAt(0);

            Node child = current.children.get(first);

            // CASE 1: No branch exists
            if (child == null) {
                Node newNode = new Node(word);
                newNode.isWord = true;
                current.children.put(first, newNode);
                return;
            }

            // Find common prefix between
            // word and compressed edge
            int common = commonPrefix(word, child.label);

            // CASE 2: Entire child label matched
            if (common == child.label.length()) {

                word = word.substring(common);

                // Exact word found
                if (word.isEmpty()) {
                    child.isWord = true;
                    return;
                }
                current = child;
            }

            // CASE 3: Partial match → SPLIT
            else {
                split(current, child, word, common);
                return;
            }
        }
    }

    private void split(Node parent, Node child, String word, int common) {
        String prefix = child.label.substring(0, common);

        String oldSuffix = child.label.substring(common);

        String newSuffix = word.substring(common);

        // Create middle node for common prefix
        Node middle = new Node(prefix);

        // Parent now points to middle
        parent.children.put(prefix.charAt(0), middle);

        // Old child goes below middle
        child.label = oldSuffix;
        middle.children.put(oldSuffix.charAt(0), child);

        // New word ends at middle - example old label: apples and new label: apple, so there'll be no new suffix for apple
        if (newSuffix.isEmpty()) {
            middle.isWord = true;
        }
        // Otherwise create new branch
        else {
            Node newNode = new Node(newSuffix);
            newNode.isWord = true;

            middle.children.put(newSuffix.charAt(0), newNode);
        }
    }

    public boolean search(String word) {
        if (word == null || word.isEmpty())
            return false;

        Node current = root;

        while (!word.isEmpty()) {

            char first = word.charAt(0);

            Node child = current.children.get(first);

            // Branch doesn't exist
            if (child == null) {
                return false;
            }

            String label = child.label;

            // Word must start with
            // the complete compressed label
            if (!word.startsWith(label)) {
                return false;
            }

            // Remove matched portion
            word = word.substring(label.length());

            // Word completely consumed
            if (word.isEmpty()) {
                return child.isWord;
            }

            // Move downward
            current = child;
        }

        return false;
    }

    private int commonPrefix(String a, String b) {
        int i = 0;

        while (i < a.length() && i < b.length() && a.charAt(i) == b.charAt(i)) {

            i++;
        }
        return i;
    }

    public static void main(String[] args) {

        CompressedTrie trie = new CompressedTrie();

        trie.insert("apple");
        trie.insert("application");
        trie.insert("apply");

        trie.insert("bat");
        trie.insert("batch");

        System.out.println(trie.search("apple")); // true
        System.out.println(trie.search("application")); // true
        System.out.println(trie.search("apply")); // true

        System.out.println(trie.search("bat")); // true
        System.out.println(trie.search("batch")); // true

        System.out.println(trie.search("app")); // false
        System.out.println(trie.search("banana")); // false
    }
}