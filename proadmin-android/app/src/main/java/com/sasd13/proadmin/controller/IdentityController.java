package com.sasd13.proadmin.controller;

import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.sasd13.proadmin.activity.IdentityActivity;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public abstract class IdentityController implements IController, IMessageHandler {

    protected IdentityActivity identityActivity;
    private View contentView;

    protected IdentityController(IdentityActivity identityActivity) {
        this.identityActivity = identityActivity;
        contentView = identityActivity.findViewById(android.R.id.content);
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
