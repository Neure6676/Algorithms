package ForOffer.OA.CloudStorageSystemI;

import java.util.TreeMap;

public class level4 {

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


    public String getFileSize(String filename) {
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
        TreeMap<String, TreeMap<Integer, Integer>> files = cur.files;
        int lastVer = files.get(folders[n - 1]).lastKey();
        return files.containsKey(folders[n - 1]) ? String.valueOf(files.get(folders[n - 1]).get(lastVer)) : "";
    }


//    public String deleteFile(String prefix) {
//        if (rootFolder == null) {
//            return "";
//        }
//        Folder cur = rootFolder;
//        String[] folders = prefix.split("/");
//        int n = folders.length;
//        for (int i = 1; i < n - 1; i++) {
//            if (!cur.nexts.containsKey(folders[i])) {
//                return "";
//            }
//            cur = cur.nexts.get(folders[i]);
//        }
//        // 假设files表示该文件夹下的所有文件
//
//    }

}
