package SystematicClass.Class18;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Code04_PrintAllPermutations {

    public static List<String> permutation1(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        ArrayList<Character> rest = new ArrayList<>();
        for (char cha : str) {
            rest.add(cha);
        }
        String path = "";
        process(rest, ans, path);
        return ans;
    }

    // 这个递归方法的可变参数设计不是很好
    // 剩下多少字符：rest
    // 之前做过的决定：ans
    public static void process(ArrayList<Character> rest, List<String> ans, String path) {
        if(rest.isEmpty()) { // base case 如果rest为空，直接把当前path加到答案中
            ans.add(path);
        } else {
            int n = rest.size();
            for (int i = 0; i < n; i++) {
                char cur = rest.get(i);
                rest.remove(i);
                process(rest, ans, path + cur);
                rest.add(i, cur); // 恢复现场
            }
        }
    }



    public static List<String> permutation2(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        process2(str, 0, ans);
        return ans;
    }

    public static void process2(char[] str, int index, List<String> ans) {
        if(index == str.length) {
            ans.add(String.valueOf(str));
        } else {
            for (int i = index; i < str.length; i++) {
                swap(str, index, i);
                process2(str, index + 1, ans);
                swap(str, index, i);
            }
        }
    }

    public static void swap(char[] str, int i, int j) {
         char temp = str[i];
         str[i] = str[j];
         str[j] = temp;
    }



    // 去重版
    public static List<String> permutation3(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        HashSet<String> set = new HashSet<>();
        process3(str, 0, set);
        ans.addAll(set); //addAll方法
        return ans;
    }

    public static void process3(char[] str, int index, HashSet<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
        } else {
            for (int i = 0; i < str.length; i++) {
                swap(str, index, i);
                process3(str, index + 1, ans);
                swap(str, index, i);
            }
        }
    }


    public static void main(String[] args) {
        String s = "acc";
        List<String> ans1 = permutation1(s);
        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans2 = permutation2(s);
        for (String str : ans2) {
            System.out.println(str);
        }

    }




}
