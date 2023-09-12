package ForOffer.OA.CloudStorageSystemI;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class level2 {

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
        rootFolder.files.put(filename, new File(filename, size)); ///
        Folder cur = rootFolder;
        String[] folders = filename.split("/");
        int n = folders.length;
        // folder
        for (int i = 1; i < n - 1; i++) {
            if (!cur.nexts.containsKey(folders[i])) {
                cur.nexts.put(folders[i], new Folder(folders[i]));
            }
            cur.files.put(filename, new File(filename, size)); ///
            cur = cur.nexts.get(folders[i]);
        }
        // file
        if (!cur.files.containsKey(filename)) {
            cur.files.put(filename, new File(folders[n - 1], size));
            return "created";
        }
        cur.files.put(filename, new File(folders[n - 1], size));
        return "overwritten";
    }


    public String getLargestN(String filePrefix, int n) {
        if (rootFolder == null) {
            return "";
        }
        Folder cur = rootFolder;
        String[] folders = filePrefix.split("/");
        int N = folders.length;
        for (int i = 1; i < N; i++) {
            if (!cur.nexts.containsKey(folders[i])) {
                return "";
            }
            cur = cur.nexts.get(folders[0]);
        }
        // There are folders and files in cur folder!
        // 此时需要这个文件夹下所有文件，并拼接上完整路径，需要遍历，且同时记录路径
        // 能不能改结构，files表示该文件夹下的所有文件？
        String ans = "";
        PriorityQueue<File> queue = new PriorityQueue<>(new FileComparator());
        for (Map.Entry e : cur.files.entrySet()) {
            queue.add((File) e.getValue());
        }
        for (int i = 0; i < n; i++) {
            File curFile = queue.poll();
            ans = ans.concat(curFile.name + "(" + curFile.size + ")");
            if (i != n-1) {
                ans = ans.concat(", ");
            }
        }
        return ans;
    }

    public static class FileComparator implements Comparator<File> {
        @Override
        public int compare(File o1, File o2) {
            return o2.size - o1.size;
        }
    }

}


