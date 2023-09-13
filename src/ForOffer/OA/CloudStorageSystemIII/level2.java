package ForOffer.OA.CloudStorageSystemIII;

import java.util.HashMap;
import java.util.Map;

public class level2 {

    HashMap<String, HashMap<String, String>> database;


    public String set(String key, String field, String value) {
        if (database == null) {
            database = new HashMap<>();
        }
        HashMap<String, String> fieldValue = new HashMap<>();
        if (database.containsKey(key)) {
            fieldValue = database.get(key);
        }
        fieldValue.put(field, value);
        database.put(key, fieldValue);
        return "";
    }

    public String get(String key, String field) {
        HashMap<String, String> fieldValue = database.get(key);
        if (fieldValue == null) {
            return "";
        }
        return fieldValue.get(field) == null ? "" : fieldValue.get(field);
    }

    public String scan(String key) {
        String ans = "";
        HashMap<String, String> fieldValue = database.get(key);
        if (fieldValue == null) {
            return ans;
        }
        for (Map.Entry e : fieldValue.entrySet()) {
            ans = ans.concat(e.getKey() +"("+e.getValue()+")"+", ");
        }
        return ans.substring(0, ans.length() - 2);
    }

    public String scanByPrefix(String key, String prefix) {
        String ans = "";
        HashMap<String, String> fieldValue = database.get(key);
        if (fieldValue == null) {
            return ans;
        }
        char[] pre = prefix.toCharArray();
        for (Map.Entry e : fieldValue.entrySet()) {
            boolean check = true;
            char[] field = e.getKey().toString().toCharArray();
            for (int i = 0; i < pre.length; i++) {
                if (field[i] != pre[i]) {
                    check = false;
                }
            }
            if (check) {
                ans = ans.concat(e.getKey() +"("+e.getValue()+")"+", ");
            }
        }
        return ans.substring(0, ans.length() - 2);
    }

}
