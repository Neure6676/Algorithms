package ForOffer.OA.CloudStorageSystemI;

public class Main {
    public static void main(String[] args) {
        level4 l = new level4();
        System.out.println(l.addFile("/file1.txt", 5));
        System.out.println(l.addFile("/file1.txt", 6));
        System.out.println(l.getFileSize("/file1.txt"));


    }
}
