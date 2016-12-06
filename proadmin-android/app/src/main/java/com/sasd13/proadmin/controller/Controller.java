package com.sasd13.proadmin.controller;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.view.IController;
import com.sasd13.proadmin.view.ProxyFragment;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public abstract class Controller implements IController {

    protected MainActivity mainActivity;
    private View contentView;
    private ProxyFragment proxyFragment;

    protected Controller(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        contentView = mainActivity.findViewById(android.R.id.content);
        proxyFragment = ProxyFragment.newInstance();
    }

    @Override
    public void displayMessage(String message) {
        Snackbar.make(contentView, message, Snackbar.LENGTH_SHORT).show();
    }

    protected void startProxyFragment() {
        mainActivity.startFragment(proxyFragment);
    }

    protected void startFragment(Fragment fragment) {
        mainActivity.startFragment(fragment);
    }

    protected boolean isProxyFragmentNotDetached() {
        return !proxyFragment.isDetached();
    }

    public void backPress() {
        mainActivity.onBackPressed();
    }
}
