package ForOffer.OA.CloudStorageSystemIII;

import java.util.HashMap;
import java.util.Map;

public class level4 {

    HashMap<String, HashMap<String, Value>> database;

    public class Value {
        String value;
        int ts;
        int ttl;

        public Value(String v, int ts) {
            value = v;
            this.ts = ts;
            ttl = 10000000;
        }

        public Value(String v, int ts, int ttl) {
            value = v;
            this.ts = ts;
            this.ttl = ttl;
        }
    }

    public String setAtWithTTL(String key, String field, String value, int ts, int ttl) {
        if (database == null) {
            database = new HashMap<>();
        }
        Value val = new Value(value, ts, ttl);
        HashMap<String, Value> fieldValue = new HashMap<>();
        if (database.containsKey(key)) {
            fieldValue = database.get(key);
        }
        fieldValue.put(field, val);
        database.put(key, fieldValue);
        return "";
    }

    public boolean deleteAt(String key, String field, int ts) {
        HashMap<String, Value> fieldValue = database.get(key);
        if (fieldValue == null) {
            return false;
        }
        Value val = fieldValue.get(field);
        if (val == null) {
            return false;
        }
        if (val.ts > ts ||val.ts + val.ttl < ts) {
            return false;
        }
        fieldValue.remove(field);
        database.put(key, fieldValue);
        return true;
    }

    public String scanAt(String key, int ts) {
        String ans = "";
        HashMap<String, Value> fieldValues = database.get(key);
        if (fieldValues == null) {
            return ans;
        }
        for (Map.Entry e : fieldValues.entrySet()) {
            Value val = (Value) e.getValue();
            if (val.ts < ts && val.ts + val.ttl > ts) {
                ans = ans.concat(e.getKey() +"("+ val.value+")"+", ");
            }
        }
        return ans.equals("") ? ans : ans.substring(0, ans.length() - 2);
    }

    public String backUp(int ts) {

    }

    public String restore(int ts, int tsToRestore) {

    }


}
