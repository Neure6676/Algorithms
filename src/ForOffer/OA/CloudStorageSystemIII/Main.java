package ForOffer.OA.CloudStorageSystemIII;

public class Main {

    public static void main(String[] args) {
//        level2 l2 = new level2();
//        System.out.println(l2.set("A", "BC", "E"));
//        System.out.println(l2.set("A", "BD", "F"));
//        System.out.println(l2.set("A", "C", "F"));
//        System.out.println(l2.get("A", "BD"));
//        System.out.println(l2.scan("A"));
//        System.out.println(l2.scanByPrefix("A", "B"));


        level3 l3 = new level3();
        System.out.println(l3.setAtWithTTL("A", "BC", "E", 1, 9));
        System.out.println(l3.setAtWithTTL("A", "BC", "E", 5, 10));
        System.out.println(l3.setAt("A", "BD", "F", 5));
        // System.out.println(l3.getAt("A", "BD", 14));
        System.out.println(l3.scanAt("A", 14));
        System.out.println(l3.scanByPrefixAt("A", "B", 14));
        System.out.println(l3.scanByPrefixAt("A", "B", 15));
    }

}
