package com.sasd13.proadmin.gui.browser;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.sasd13.proadmin.activities.ProjectsActivity;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.SettingsActivity;

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
                    context.getResources().getString(R.string.activity_settings),
                    ContextCompat.getDrawable(context, R.drawable.ic_settings_black_24dp),
                    ContextCompat.getColor(context, R.color.brown),
                    SettingsActivity.class
            ));

            items.add(new BrowserItemModel(
                    context.getResources().getString(R.string.activity_projects),
                    ContextCompat.getDrawable(context, R.drawable.ic_project_small),
                    ContextCompat.getColor(context, R.color.orange),
                    ProjectsActivity.class
            ));
        }

        return items;
    }
}
