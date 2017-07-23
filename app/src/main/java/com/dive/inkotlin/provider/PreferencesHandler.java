package com.dive.inkotlin.provider;

import com.orhanobut.hawk.Hawk;
import com.dive.inkotlin.Details;

import java.util.ArrayList;
import java.util.List;

public class PreferencesHandler {

    public static void saveIntValue(String key, int value) {
        String localKey = getKeyPrefix(key);
        Hawk.put(localKey, value);
    }

    public static int loadIntValue(String key) {
        String localKey = getKeyPrefix(key);
        int value = Hawk.get(localKey, 0);
        return value;
    }

    public static void saveStringValue(String key, String value) {
        String localKey = getKeyPrefix(key);
        Hawk.put(localKey, value);
    }

    public static String loadStringValue(String key) {
        String localKey = getKeyPrefix(key);
        String value = Hawk.get(localKey, "");
        return value;
    }

    public static void saveLongValue(String key, long value) {
        String localKey = getKeyPrefix(key);
        Hawk.put(localKey, value);
    }

    public static long loadLongValue(String key) {
        String localKey = getKeyPrefix(key);
        long value = Hawk.get(localKey, 0L);
        return value;
    }

    public static void saveListValue(String key, List<Integer> value) {
        String localKey = getKeyPrefix(key);
        Hawk.put(localKey, value);
    }

    public static List<Integer> loadListValue(String key) {
        String localKey = getKeyPrefix(key);
        List<Integer> value = Hawk.get(localKey, new ArrayList<>(0));
        return value;
    }

    private static String getKeyPrefix(String key) {
        if (Details.getPreferencesKeyPrefix().isEmpty())
            return key;
        return Details.getPreferencesKeyPrefix() + key;
    }

}
