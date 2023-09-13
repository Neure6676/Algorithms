package ForOffer.OA.CloudStorageSystemIII;

import java.util.HashMap;

public class level1 {

    HashMap<String, HashMap<String, String>> database;


    public void set(String key, String field, String value) {
        if (database == null) {
            database = new HashMap<>();
        }
        HashMap<String, String> fieldValue = new HashMap<>();
        fieldValue.put(field, value);
        database.put(key, fieldValue);
    }

    public String get(String key, String field) {
        HashMap<String, String> fieldValue = database.get(key);
        if (fieldValue == null) {
            return "";
        }
        return fieldValue.get(field) == null ? "" : fieldValue.get(field);
    }

    public boolean delete(String key, String field) {
        HashMap<String, String> fieldValue = database.get(key);
        if (fieldValue == null) {
            return false;
        }
        if (fieldValue.get(field) == null) {
            return false;
        }
        fieldValue.remove(field);
        database.put(key, fieldValue);
        return true;
    }

}
