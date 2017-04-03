package com.sasd13.proadmin.controller;

import android.app.Activity;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public abstract class Controller implements IController {

    private View contentView;

    protected Controller(Activity activity) {
        contentView = activity.findViewById(android.R.id.content);
    }

    @Override
    public void display(@StringRes int messageId) {
        Snackbar.make(contentView, messageId, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void display(String message) {
        Snackbar.make(contentView, message, Snackbar.LENGTH_SHORT).show();
    }
}
