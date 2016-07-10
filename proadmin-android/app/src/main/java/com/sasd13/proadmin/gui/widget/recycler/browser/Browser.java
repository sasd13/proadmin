package com.sasd13.proadmin.gui.widget.recycler.browser;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.SettingActivity;

import java.util.ArrayList;
import java.util.List;

public class Browser {

    private static class BrowserHolder {
        private static final Browser INSTANCE = new Browser();
    }

    private List<BrowserItemModel> items;

    private Browser() {
        items = new ArrayList<>();
    }

    public static Browser getInstance() {
        return BrowserHolder.INSTANCE;
    }

    public List<BrowserItemModel> getItems(final Context context) {
        if (items.isEmpty()) {
            items.add(new BrowserItemModel(
                    context.getResources().getString(R.string.activity_setting),
                    ContextCompat.getDrawable(context, R.drawable.ic_setting_small),
                    ContextCompat.getColor(context, R.color.brown),
                    SettingActivity.class
            ));
        }

        return items;
    }
}
