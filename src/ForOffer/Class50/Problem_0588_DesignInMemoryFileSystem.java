package ForOffer.Class50;

import java.util.*;

public class Problem_0588_DesignInMemoryFileSystem {

    class FileSystem {

        public class Node {
            // file name or path name
            public String name;
            // content == null : path
            // content != null : folder
            public StringBuilder content;
            public TreeMap<String, Node> nexts;

            // folder constructor
            public Node(String n) {
                name = n;
                content = null;
                nexts = new TreeMap<>();
            }

            // file constructor
            public Node(String n, String c) {
                name = n;
                content = new StringBuilder(c);
                nexts = new TreeMap<>();
            }
        }

        public Node rootNode;

        public FileSystem() {
            rootNode = new Node("");
        }


        public List<String> ls(String path) {
            Node cur = rootNode;
            List<String> ans = new ArrayList<>();
            String[] folders = path.split("/");
            int n = folders.length;
            for (int i = 1; i < n; i++) {
                cur = cur.nexts.get(folders[i]);
            }
            if (cur.content != null) {
                // file
                ans.add(cur.name);
            } else {
                ans.addAll(cur.nexts.keySet());
            }
            return ans;
        }

        // Makes a new directory according to the given path.
        public void mkdir(String path) {
            Node cur = rootNode;
            String[] folders = path.split("/");
            for (int i = 1; i < folders.length; i++) {
                if (cur.nexts.get(folders[i]) == null) {
                    cur.nexts.put(folders[i], new Node(folders[i]));
                }
                cur = cur.nexts.get(folders[i]);
            }
        }

        // ["/a/b/c/d", "hello"]
        public void addContentToFile(String filePath, String content) {
            Node cur = rootNode;
            String[] folders = filePath.split("/");
            int n = folders.length;
            for (int i = 1; i < n - 1; i++) {
                if (cur.nexts.get(folders[i]) == null) {
                    cur.nexts.put(folders[i], new Node(folders[i]));
                }
                cur = cur.nexts.get(folders[i]);
            }
            if (cur.nexts.get(folders[n - 1]) == null) {
                cur.nexts.put(folders[n - 1], new Node(folders[n - 1], ""));
            }
            cur.nexts.get(folders[n-1]).content.append(content);

        }

        // Returns the content in the file at filePath
        public String readContentFromFile(String filePath) {
            Node cur = rootNode;
            String[] folders = filePath.split("/");
            for (int i = 1; i < folders.length; i++) {
                cur = cur.nexts.get(folders[i]);
            }
            return cur.content.toString();
        }
    }


}
