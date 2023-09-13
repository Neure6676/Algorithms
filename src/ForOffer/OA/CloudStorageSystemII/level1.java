package ForOffer.OA.CloudStorageSystemII;

import java.util.TreeMap;

public class level1 {

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

    FolderNode rootNode;

    public boolean addFile(String filename, int size) {
        if (rootNode == null ) {
            rootNode = new FolderNode("");
        }
        FolderNode cur = rootNode;
        String[] folders = filename.split("/");
        int N = folders.length;
        if (N > 2) {
            for (int i = 1; i < N; i++) {
                if (!cur.nexts.containsKey(folders[i])) {
                    cur.nexts.put(folders[i], new FolderNode(folders[i]));
                }
                cur = cur.nexts.get(folders[i]);
            }
        }
        if (cur.files.containsKey(folders[N - 1])) {
            return false;
        }
        cur.files.put(folders[N - 1], size);
        return true;
    }

    public boolean moveFile(String from, String to) {
        if (rootNode == null) {
            return false;
        }
        FolderNode cur = rootNode;
        String[] fromFolder = from.split("/");
        int N = fromFolder.length;
        if (N > 2) {
            for (int i = 1; i < N; i++) {
                if (!cur.nexts.containsKey(fromFolder[i])) {
                    return false;
                }
                cur = cur.nexts.get(fromFolder[i]);
            }
        }
        if (!cur.files.containsKey(fromFolder[N - 1])) {
            return false;
        }
        String fileName = fromFolder[N - 1];
        int size = cur.files.get(fromFolder[N - 1]);
        cur = rootNode;
        String[] toFolder = from.split("/");
        N = toFolder.length;
        if (N > 2) {
            for (int i = 1; i < N; i++) {
                if (!cur.nexts.containsKey(toFolder[i])) {
                    return false;
                }
                cur = cur.nexts.get(toFolder[i]);
            }
        }
        cur.files.put(fileName, size);
        return true;
    }

    public int getFileSize(String filename) {
        if (rootNode == null) {
            return 0;
        }
        FolderNode cur = rootNode;
        String[] Folder = filename.split("/");
        int N = Folder.length;
        if (N > 2) {
            for (int i = 1; i < N; i++) {
                if (!cur.nexts.containsKey(Folder[i])) {
                    return 0;
                }
                cur = cur.nexts.get(Folder[i]);
            }
        }
        if (!cur.files.containsKey(Folder[N - 1])) {
            return 0;
        }
        return cur.files.get(Folder[N - 1]);
    }

}
