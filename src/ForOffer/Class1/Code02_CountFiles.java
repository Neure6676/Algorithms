package ForOffer.Class1;

import java.io.File;
import java.util.Stack;

/**
 * 给定一个文件目录的路径，写一个函数统计这个目录下所有的文件数量并返回，隐藏文件也算，但是文件夹不算
 *
 * BFS 队列
 * 1）Q内弹出文件夹cur
 * 2）文件：count++   文件夹：入Q
 */
public class Code02_CountFiles {

    public static int getFileNumber(String folderPath) {
        File root = new File(folderPath);
        if (!root.isFile() && !root.isDirectory()) {
            return 0;
        }
        if (root.isFile()) {
            return 1;
        }
        int files = 0;
        Stack<File> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            File cur = stack.pop();
            for (File next : cur.listFiles()) {
                if (next.isFile()) {
                    files++;
                }
                if (next.isDirectory()) {
                    stack.push(next);
                }
            }
        }
        return files;
    }

    public static void main(String[] args) {
        String path = "/Users/zhenyuanzhang/Desktop/Algorithm/src/SystematicClass";
        System.out.println(getFileNumber(path));
    }

}
