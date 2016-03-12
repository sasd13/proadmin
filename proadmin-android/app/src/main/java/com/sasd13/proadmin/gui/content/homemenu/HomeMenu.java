package com.sasd13.proadmin.gui.content.homemenu;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.SettingActivity;

public class HomeMenu {

    private static HomeMenu instance = null;

    private static final int SIZE = 1;
    private HomeMenuItem[] tab;

    private HomeMenu(Context context) {
        tab = new HomeMenuItem[SIZE];

        tab[0] = new HomeMenuItem(
                context.getResources().getString(R.string.activity_setting),
                ContextCompat.getDrawable(context, R.drawable.ic_setting_small),
                ContextCompat.getColor(context, R.color.customBrown),
                new Intent(context, SettingActivity.class)
        );
    }

    public static HomeMenu getInstance(Context context) {
        if (instance == null) {
            synchronized (HomeMenu.class) {
                if (instance == null) {
                    instance = new HomeMenu(context);
                }
            }
        }

        return instance;
    }

    public HomeMenuItem[] getItems() { return tab; }
}
