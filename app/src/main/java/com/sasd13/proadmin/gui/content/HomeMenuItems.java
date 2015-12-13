package com.sasd13.proadmin.gui.content;

import android.content.Context;
import android.content.Intent;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.SettingActivity;

public class HomeMenuItems {

    private static HomeMenuItems instance = null;

    private static final int SIZE = 1;
    private HomeMenuItem[] tab;

    private HomeMenuItems(Context context) {
        this.tab = new HomeMenuItem[SIZE];

        this.tab[0] = new HomeMenuItem(
                context.getResources().getString(R.string.activity_setting),
                context.getResources().getDrawable(R.drawable.ic_setting_small),
                context.getResources().getColor(R.color.customBrown),
                new Intent(context, SettingActivity.class)
        );
    }

    public static synchronized HomeMenuItems getInstance(Context context) {
        if (instance == null) {
            instance = new HomeMenuItems(context);
        }

        return instance;
    }

    public HomeMenuItem[] getItems() { return this.tab; }
}
