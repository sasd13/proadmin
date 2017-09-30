package com.sasd13.proadmin.android.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.sasd13.proadmin.android.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by ssaidali2 on 30/09/2017.
 */

public class AppProperties {

    private static Properties properties = new Properties();

    public static void init(Context context) {
        InputStream rawResource = null;

        try {
            rawResource = context.getResources().openRawResource(R.raw.ope);

            properties.load(rawResource);
        } catch (Resources.NotFoundException e) {
            Log.e("Properties", "Unable to find the config file: " + e.getMessage());
        } catch (IOException e) {
            Log.e("Properties", "Failed to open config file.");
        } finally {
            if (rawResource != null) {
                try {
                    rawResource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);

        if (value == null) {
            Log.e("Properties", "property key '" + key + "' is unknown");
        }

        return value;
    }
}
