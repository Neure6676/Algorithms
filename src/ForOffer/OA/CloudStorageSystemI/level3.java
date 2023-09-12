package ForOffer.OA.CloudStorageSystemI;

import java.util.TreeMap;

public class level3 {

    public class Folder {
        public String name;
        public TreeMap<String, Folder> nexts;
        // <name, <ver, size>>
        public TreeMap<String, TreeMap<Integer, Integer>> files;

        public Folder(String n) {
            name = n;
            nexts = new TreeMap<>();
            files = new TreeMap<>();
        }
    }

    public Folder rootFolder;


    public String addFile(String filename, int size) {
        if (rootFolder == null) {
            rootFolder = new Folder("");
        }
        Folder cur = rootFolder;
        String[] folders = filename.split("/");
        int n = folders.length;
        // folder
        if (n > 2) {
            for (int i = 1; i < n - 1; i++) {
                if (!cur.nexts.containsKey(folders[i])) {
                    cur.nexts.put(folders[i], new Folder(folders[i]));
                }
                cur = cur.nexts.get(folders[i]);
            }
        }
        // file
        TreeMap<Integer, Integer> curFile = new TreeMap<>();
        if (!cur.files.containsKey(folders[n - 1])) {
            curFile.put(1, size);
            cur.files.put(folders[n - 1], curFile);
            return "created";
        }
        int oldVer = cur.files.get(folders[n - 1]).lastKey();
        curFile = cur.files.get(folders[n - 1]);
        curFile.put(oldVer + 1, size);
        cur.files.put(folders[n - 1], curFile);
        return "overwritten";
    }


    public String getVersion(String filename, int ver) {
        if (rootFolder == null) {
            return "";
        }
        Folder cur = rootFolder;
        String[] folders = filename.split("/");
        int n = folders.length;
        if (n > 2) {
            for (int i = 1; i < n - 1; i++) {
                if (!cur.nexts.containsKey(folders[i])) {
                    return "";
                }
                cur = cur.nexts.get(folders[i]);
            }
        }
        if (!cur.files.containsKey(folders[n - 1]) || !cur.files.get(folders[n - 1]).containsKey(ver)) {
            return "";
        } else {
            return String.valueOf(cur.files.get(folders[n - 1]).get(ver));
        }
    }


    public String deleteVersion(String filename, int ver) {
        if (rootFolder == null) {
            return "false";
        }
        Folder cur = rootFolder;
        String[] folders = filename.split("/");
        int n = folders.length;
        if (n > 2) {
            for (int i = 1; i < n - 1; i++) {
                if (!cur.nexts.containsKey(folders[i])) {
                    return "false";
                }
                cur = cur.nexts.get(folders[i]);
            }
        }
        TreeMap<Integer, Integer> curFile = cur.files.get(folders[n - 1]);
        if (!cur.files.containsKey(folders[n - 1]) || !curFile.containsKey(ver)) {
            return "false";
        } else {
            curFile.remove(ver);
            for (int i = ver + 1; i <= curFile.lastKey(); i++) {
                curFile.put(i - 1, curFile.get(i));
            }
            return "true";
        }
    }





}
