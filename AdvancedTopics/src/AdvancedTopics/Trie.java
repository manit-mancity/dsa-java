package AdvancedTopics;

import java.util.Stack;

public class Trie {
    private static class Node{
        Node[] next = new Node[26];
        int pass =0;
        int end =0;
    }
    private static final Node root = new Node();
    public static void insert(String word){
        if(word ==null)return;
        Node cur = root;
        cur.pass++;

        for(int i =0;i<word.length();i++){
            char c = word.charAt(i);

            int idx = c - 'a';
            if(cur.next[idx]==null) cur.next[idx]=new Node();

            cur = cur.next[idx];
            cur.pass++;
        }
        cur.end++;
    }
    public boolean contains(String word){
        Node n = walk(word);
        return n != null && n.end > 0;
    }
    public boolean startsWith(String prefix){
        return walk(prefix) != null;
    }

    public int countPrefix(String prefix){
        Node n = walk(prefix);
        return n == null ? 0 : n.pass;
    }
    private Node walk(String s){
        if(s==null)return null;
        Node cur = root;
        for(int i =0;i<s.length();i++){
            char c = s.charAt(i);
            if(c<'a'||c>'z') return null;
            int idx = c -'a';
            if(cur.next[idx]==null)return null;
            cur = cur.next[idx];
        }
        return cur;
    }
    public boolean delete(String word){
        if (!contains(word)) return false;

        Stack<Node> stack = new Stack<>();
        Stack<Integer> pathIdx = new Stack<>();

        Node cur = root;
        stack.push(cur);

        for(int i =0;i<word.length();i++){
            int idx = word.charAt(i)-'a';
            cur=cur.next[idx];
            stack.push(cur);
            pathIdx.push(idx);
        }
        cur.end--;

        for(int i = word.length();i>=0;i--){
            Node node = stack.pop();
            node.pass--;
            if(i>0){
                Node parent = stack.pop();
                int idx = pathIdx.pop();
                if(node.pass==0)parent.next[idx] = null;
            }
        }return true;

    }
    public static void main(String[] args) {

        Trie t = new Trie();

        // Insert words
        t.insert("apple");
        t.insert("app");
        t.insert("application");
        t.insert("apply");
        t.insert("banana");

        // contains()
        System.out.println("Contains apple: " + t.contains("apple"));
        System.out.println("Contains app: " + t.contains("app"));
        System.out.println("Contains appl: " + t.contains("appl"));
        System.out.println("Contains banana: " + t.contains("banana"));
        System.out.println();

        // startsWith()
        System.out.println("Starts with app: " + t.startsWith("app"));
        System.out.println("Starts with appl: " + t.startsWith("appl"));
        System.out.println("Starts with ban: " + t.startsWith("ban"));
        System.out.println("Starts with cat: " + t.startsWith("cat"));
        System.out.println();

        // countPrefix()
        System.out.println("Words starting with 'app': " + t.countPrefix("app"));
        System.out.println("Words starting with 'appl': " + t.countPrefix("appl"));
        System.out.println("Words starting with 'ban': " + t.countPrefix("ban"));
        System.out.println("Words starting with 'cat': " + t.countPrefix("cat"));
        System.out.println();

        // Delete
        System.out.println("Delete apple: " + t.delete("apple"));
        System.out.println("Contains apple: " + t.contains("apple"));
        System.out.println("Contains app: " + t.contains("app"));
        System.out.println("Words starting with 'app': " + t.countPrefix("app"));
        System.out.println();

        // Try deleting something that doesn't exist
        System.out.println("Delete apple again: " + t.delete("apple"));
        System.out.println("Delete mango: " + t.delete("mango"));
    }

}

