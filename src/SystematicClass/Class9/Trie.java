package SystematicClass.Class9;

public class Trie {

    public static class Node1 {
        private int pass;
        private int end;
        private Node1[] nexts;

        public Node1() {
            pass = 0;
            end = 0;
            nexts = new Node1[26];  //若next[i] == null 则表示i方向的路径不存在
        }

        public static class Trie1 {
            private Node1 root;

            public Trie1() {
                root = new Node1();
            }

            public void insert(String word) {
                if (word == null) {
                    return;
                }
                char[] str = word.toCharArray();  //"abc" -> ["a", "b", "c"]
                Node1 node = root;
                node.pass++;
                int path = 0;
                for (int i = 0; i < str.length; i++) {
                    path = str[i] - 'a'; // 用ASCII码确定要走哪条路，如"a"走0
                    if (node.nexts[path] == null) {
                        node.nexts[path] = new Node1();
                    }
                    node = node.nexts[path];
                    node.pass++;
                }
                node.end++;
            }

            //word这个单词之前加入过几次
            public int search(String word) {
                if (word == null) {
                    return 0;
                }
                char[] str = word.toCharArray();
                Node1 node = root;
                int path = 0;
                for (int i = 0; i < str.length; i++) {
                    path = str[i] - 'a';
                    if (node.nexts[path] == null) {
                        return 0;
                    }
                    node = node.nexts[path];
                }
                return node.end;
            }

            //所有加入的字符串中，有几个是以pre作为前缀的
            public int prefixNumber(String word) {
                if (word == null) {
                    return 0;
                }
                char[] str = word.toCharArray();
                Node1 node = root;
                int path = 0;
                for (int i = 0; i < str.length; i++) {
                    path = str[i] - 'a';
                    if (node.nexts[path] == null) {
                        return 0;
                    }
                    node = node.nexts[path];
                }
                return node.pass;
            }

            //删除的重点：不让内存泄露：没用的节点删掉
            public void delete(String word) {
                if (search(word) != 0) {
                    char[] str = word.toCharArray();
                    Node1 node = root;
                    node.pass--;
                    int path = 0;
                    for (int i = 0; i < str.length; i++) {
                        path = str[i] - 'a';
                        if (--node.nexts[path].pass == 0) {
                            node.nexts[path] = null;
                            return;
                        }
                        node = node.nexts[path];
                    }
                    node.end--;
                }
            }
        }
    }


}
