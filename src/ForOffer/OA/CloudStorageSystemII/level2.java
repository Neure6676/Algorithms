package ForOffer.OA.CloudStorageSystemII;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class level2 {

    public class FolderNode{
        String name;
        TreeMap<String, FolderNode> nexts;
        TreeMap<String, Integer> files;

        public FolderNode(String n) {
            name = n;
            nexts = new TreeMap<>();
            files = new TreeMap<>();
        }
    }

    public class File{
        String name;
        int size;

        public File(String n, int s) {
            name = n;
            size  = s;
        }

    }

    public static class MyComparator implements Comparator<File> {
        @Override
        public int compare(File o1, File o2) {
            return o2.size - o1.size;
        }
    }

    FolderNode rootNode;

    public boolean addFile(String filename, int size) {
        if (rootNode == null ) {
            rootNode = new FolderNode("");
            rootNode.files.put(filename, size);
        }
        FolderNode cur = rootNode;
        String[] folders = filename.split("/");
        int N = folders.length;
        if (N > 2) {
            for (int i = 1; i < N; i++) {
                if (!cur.nexts.containsKey(folders[i])) {
                    cur.nexts.put(folders[i], new FolderNode(folders[i]));
                }
                cur.files.put(filename, size);
                cur = cur.nexts.get(folders[i]);
            }
        }
        if (cur.files.containsKey(filename)) {
            return false;
        }
        cur.files.put(filename, size);
        return true;
    }



    public String findFile(String prefix, String suffix) {
        if (rootNode == null) {
            return "";
        }
        FolderNode cur = rootNode;
        String[] folders = prefix.split("/");
        int n = folders.length;
        if (n > 1) {
            for (int i = 1; i < n; i++) {
                if (!cur.nexts.containsKey(folders[i])) {
                    return "";
                }
                cur = cur.nexts.get(folders[i]);
            }
        }
        String ans = "";
        PriorityQueue<File> queue = new PriorityQueue<>(new MyComparator());
        for (Map.Entry e : cur.files.entrySet()) {
            queue.add(new File((String) e.getKey(), (Integer) e.getValue()));
        }
        for (int i = 0; i < n; i++) {
            File curFile = queue.poll();
            // TODO
            // get rid of those isn't end in suffix
            ans = ans.concat(curFile.name + "(" + curFile.size + ")");
            if (i != n-1) {
                ans = ans.concat(", ");
            }
        }
        return ans;

    }
}
