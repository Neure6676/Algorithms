package UltimateAlgo2024.SetAndMap;

import java.util.HashMap;
import java.util.HashSet;

public class Code01_HashSetAndHashMap {


    public static void main(String[] args) {
        // Integer、Long、Double、Float
        // Byte、Short、Character、Boolean
        // String等都有这个特征
        String str1 = new String("Hello");
        String str2 = new String("Hello");
        // false，因为不同的内存地址
        System.out.println(str1 == str2);
        // true，因为它们的值是相同的
        System.out.println(str1.equals(str2));

        HashSet<String> set = new HashSet<>();
        set.add(str1);
        System.out.println(set.contains("Hello"));
        // true 会看值是否相等
        System.out.println(set.contains(str2));
        set.add(str2);
        // 1 只关心值
        System.out.println(set.size());
        set.remove(str1);
        set.clear();
        System.out.println(set.isEmpty());

        System.out.println("===========");

        HashMap<String, String> map1 = new HashMap<>();
        map1.put(str1, "World");
        System.out.println(map1.containsKey("Hello"));
        // true 只关心值
        System.out.println(map1.containsKey(str2));
        // 只要字面值一致就认为对象是同一个
        System.out.println(map1.get(str2));
        System.out.println(map1.get("Zane") == null);
        map1.remove("Hello");
        System.out.println(map1.size());
        map1.clear();
        System.out.println(map1.isEmpty());

        System.out.println("===========");

        // 一般在笔试中，未必需要申请哈希表
        HashMap<Integer, Integer> map2 = new HashMap<>();
        map2.put(56, 7285);
        map2.put(34, 3671263);
        map2.put(17, 716311);
        map2.put(24, 1263161);
        // 上面的map2行为，可以被如下数组的行为替代
        int[] arr = new int[100];
        arr[56] = 7285;
        arr[34] = 3671263;
        arr[17] = 716311;
        arr[24] = 1263161;
        // 哈希表的增、删、改、查，都可以被数组替代，前提是key的范围是固定的、可控的
        System.out.println("Replace map with array!");

        System.out.println("===========");
        Student s1 = new Student(24, "Zhenyuan");
        Student s2 = new Student(24, "Zhenyuan");
        // 除了那八种，如Student，会用内存地址作key
        HashMap<Student, String> map3 = new HashMap<>();
        map3.put(s1, "This is Zhenyuan");
        // t
        System.out.println(map3.containsKey(s1));
        // f
        System.out.println(map3.containsKey(s2));
        map3.put(s2, "This is the other Zhenyuan");
        System.out.println(map3.size());
        System.out.println(map3.get(s1));
        System.out.println(map3.get(s2));
    }

    public static class Student {
        public int age;
        public String name;

        public Student(int a, String b) {
            age = a;
            name = b;
        }
    }


}
