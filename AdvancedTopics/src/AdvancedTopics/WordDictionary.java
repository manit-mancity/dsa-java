import AdvancedTopics.Trie;

class WordDictionary {

    private static class Node{
        Node[] next = new Node[26];
        int pass =0;
        boolean end;
    }
    private static final Node root = new Node();
    public WordDictionary() {
    }
    public void addWord(String word) {
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
        cur.end = true;
    }

    public boolean search(String word) {
        return dfs(root, word, 0);
    }
    private static boolean dfs(Node cur, String w, int i){
        if(cur==null)return false;
        if(i==w.length()) return cur.end;
        char c = w.charAt(i);
        if(c=='.'){
            for(Node child : cur.next){
                if(child != null && dfs(child, w, i+1)){
                    return true;
                }
            }return false;
        }else{
            return dfs(cur.next[c-'a'], w, i+1);
        }


    }
    public static void main(String [] args){
        WordDictionary w = new WordDictionary();

    }
}
