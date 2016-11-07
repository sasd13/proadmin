package com.sasd13.proadmin.gui.browser;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.HomeActivity;
import com.sasd13.proadmin.activities.ProjectsActivity;
import com.sasd13.proadmin.activities.RunningsActivity;
import com.sasd13.proadmin.activities.SettingsActivity;
import com.sasd13.proadmin.activities.TeamsActivity;

import java.util.ArrayList;
import java.util.List;

public class Browser {

    private static class BrowserHolder {
        private static final Browser INSTANCE = new Browser();
    }

    private List<BrowserItemModel> navItems, accountItems;

    private Browser() {
        navItems = new ArrayList<>();
        accountItems = new ArrayList<>();
    }

    public static Browser getInstance() {
        return BrowserHolder.INSTANCE;
    }

    public List<BrowserItemModel> getNavItems(final Context context) {
        if (navItems.isEmpty()) {
            navItems.add(new BrowserItemModel(
                    context.getResources().getString(R.string.activity_projects),
                    ContextCompat.getDrawable(context, R.drawable.ic_project_small),
                    ContextCompat.getColor(context, R.color.orange),
                    ProjectsActivity.class
            ));
            navItems.add(new BrowserItemModel(
                    context.getResources().getString(R.string.activity_runnings),
                    ContextCompat.getDrawable(context, R.drawable.ic_settings_black_24dp),
                    ContextCompat.getColor(context, R.color.red),
                    RunningsActivity.class
            ));
            navItems.add(new BrowserItemModel(
                    context.getResources().getString(R.string.activity_teams),
                    ContextCompat.getDrawable(context, R.drawable.ic_team_small),
                    ContextCompat.getColor(context, R.color.green),
                    TeamsActivity.class
            ));
        }

        return navItems;
    }

    public List<BrowserItemModel> getAccountItems(final Context context) {
        if (accountItems.isEmpty()) {
            accountItems.add(new BrowserItemModel(
                    context.getResources().getString(R.string.activity_settings),
                    ContextCompat.getDrawable(context, R.drawable.ic_settings_black_24dp),
                    ContextCompat.getColor(context, R.color.brown),
                    SettingsActivity.class
            ));
            accountItems.add(new BrowserItemModel(
                    context.getResources().getString(R.string.drawer_label_logout),
                    ContextCompat.getDrawable(context, R.drawable.ic_exit_to_app_black_24dp),
                    ContextCompat.getColor(context, R.color.greyBackground),
                    HomeActivity.class
            ));
        }

        return accountItems;
    }
}
