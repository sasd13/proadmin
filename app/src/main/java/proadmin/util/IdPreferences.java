package proadmin.util;

import java.util.prefs.Preferences;

/**
 * Created by Samir on 14/06/2015.
 */
public class IdPreferences {

    private static final String KEY_COUNT_ID = "count_id";

    private static Preferences getPreferences(String className) {
        return Preferences.userRoot().node(className);
    }

    public static int getCountId(String className) {
        Preferences preferences = getPreferences(className);

        return preferences.getInt(KEY_COUNT_ID, 0);
    }

    public static void setCountId(String className, int countId) {
        Preferences preferences = getPreferences(className);

        preferences.putInt(KEY_COUNT_ID, countId);
    }
}
