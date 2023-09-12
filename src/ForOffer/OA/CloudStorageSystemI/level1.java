package ForOffer.OA.CloudStorageSystemI;

import java.util.Objects;
import java.util.TreeMap;

public class level1 {

    public class Folder {
        public String name;
        public TreeMap<String, Folder> nexts;
        public TreeMap<String, File> files;

        public Folder(String n) {
            name = n;
            nexts = new TreeMap<>();
            files = new TreeMap<>();
        }
    }

    public class File {
        public String name;
        public int size;
        // public StringBuilder content;

        public File(String n, int s) {
            name = n;
            size = s;
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
        for (int i = 1; i < n - 1; i++) {
            if (!cur.nexts.containsKey(folders[i])) {
                cur.nexts.put(folders[i], new Folder(folders[i]));
            }
            cur = cur.nexts.get(folders[i]);
        }
        // file
        if (!cur.files.containsKey(folders[n - 1])) {
            cur.files.put(folders[n - 1], new File(folders[n - 1], size));
            return "created";
        }
        cur.files.put(folders[n - 1], new File(folders[n - 1], size));
        return "overwritten";
    }


    public String getFileSize(String filename) {
        if (rootFolder == null) {
            return "";
        }
        Folder cur = rootFolder;
        String[] folders = filename.split("/");
        int n = folders.length;
        for (int i = 1; i < n - 1; i++) {
            if (!cur.nexts.containsKey(folders[i])) {
                return "";
            }
            cur = cur.nexts.get(folders[i]);
        }
        return cur.files.containsKey(folders[n - 1]) ? String.valueOf(cur.files.get(folders[n - 1]).size) : "";
    }


    public String moveFile(String from, String to) {
        if (Objects.equals(getFileSize(from), "")) {
            return "false";
        }
        Folder cur = rootFolder;
        String[] folders = from.split("/");
        int n = folders.length;
        for (int i = 1; i < n - 1; i++) {
            cur = cur.nexts.get(folders[i]);
        }
        String newFileName = folders[n - 1];
        int newFileSize = cur.files.get(newFileName).size;
        cur.files.remove(newFileName);
        // find new route
        folders = to.split("/");
        n = folders.length;
        for (int i = 1; i < n - 1; i++) {
            cur = cur.nexts.get(folders[i]);
        }
        cur.files.put(newFileName, new File(newFileName, newFileSize));
        return "true";
    }



}
