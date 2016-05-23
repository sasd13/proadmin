package com.sasd13.proadmin.gui.nav;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.SettingActivity;

public class Nav {

    private static Nav instance = null;

    private static final int SIZE = 1;
    private NavItem[] tab;

    private Nav(Context context) {
        tab = new NavItem[SIZE];

        tab[0] = new NavItem(
                context.getResources().getString(R.string.activity_setting),
                ContextCompat.getDrawable(context, R.drawable.ic_setting_small),
                ContextCompat.getColor(context, R.color.brown),
                new Intent(context, SettingActivity.class)
        );
    }

    public static Nav getInstance(Context context) {
        if (instance == null) {
            synchronized (Nav.class) {
                if (instance == null) {
                    instance = new Nav(context);
                }
            }
        }

        return instance;
    }

    public NavItem[] getItems() { return tab; }
}
