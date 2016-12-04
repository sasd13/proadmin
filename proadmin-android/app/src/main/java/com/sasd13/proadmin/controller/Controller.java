package com.sasd13.proadmin.controller;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.view.IController;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public abstract class Controller implements IController {

    protected MainActivity mainActivity;
    private View contentView;
    private boolean backStacked;

    protected Controller(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        contentView = mainActivity.findViewById(android.R.id.content);
        backStacked = true;
    }

    @Override
    public void displayMessage(String message) {
        Snackbar.make(contentView, message, Snackbar.LENGTH_SHORT).show();
    }

    public void setBackStacked(boolean backStacked) {
        this.backStacked = backStacked;
    }

    protected void startFragment(Fragment fragment) {
        if (backStacked) {
            mainActivity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_container_fragment, fragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            mainActivity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_container_fragment, fragment)
                    .commit();
        }
    }

    public void backPress() {
        mainActivity.onBackPressed();
    }
}
