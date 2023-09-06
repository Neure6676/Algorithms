package ForOffer.Class50;

class Trie {

    public static class TrieNode {
        public int pass;
        public int end;
        public TrieNode[] nexts;
        // public HashMap<Integer, TrieNode> nexts;

        public TrieNode() {
            pass = 0;
            end = 0;
            nexts = new TrieNode[26];
        }
    }

    public TrieNode rootNode;

    public Trie() {
        rootNode = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = rootNode;
        node.pass++;
        for (int i = 0; i < word.length(); i++) {
            char cur = word.charAt(i);
            int way = cur - 'a';
            if (node.nexts[way] == null) {
                node.nexts[way] = new TrieNode();
            }
            node = node.nexts[way];
            node.pass++;
        }
        node.end++;
    }

    public int countWordsEqualTo(String word) {
        TrieNode node = rootNode;
        for (int i = 0; i < word.length(); i++) {
            char cur = word.charAt(i);
            int way = cur - 'a';
            if (node.nexts[way] == null) {
                return 0;
            }
            node = node.nexts[way];
        }
        return node.end;
    }

    public int countWordsStartingWith(String prefix) {
        TrieNode node = rootNode;
        for (int i = 0; i < prefix.length(); i++) {
            char cur = prefix.charAt(i);
            int way = cur - 'a';
            if (node.nexts[way] == null) {
                return 0;
            }
            node = node.nexts[way];
        }
        return node.pass;
    }

    public void erase(String word) {
        TrieNode node = rootNode;
        node.pass--;
        for (int i = 0; i < word.length(); i++) {
            char cur = word.charAt(i);
            int way = cur - 'a';
            node = node.nexts[way];
            node.pass--;
        }
        node.end--;
    }
}
