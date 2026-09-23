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
            if(cur.next[idx]==null) cur.next[idx]=new Node;

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
    public static void main(String [] args){
        
    }

}

