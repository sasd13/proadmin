package com.sasd13.proadmin.controller;

import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.fragment.ProxyFragment;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public abstract class MainController implements IController, IMessageHandler {

    protected MainActivity mainActivity;
    private View contentView;
    private ProxyFragment proxyFragment;
    private boolean loadingNext;

    protected MainController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        contentView = mainActivity.findViewById(android.R.id.content);
        proxyFragment = ProxyFragment.newInstance();
    }

    @Override
    public void display(@StringRes int messageId) {
        Snackbar.make(contentView, messageId, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void display(String message) {
        Snackbar.make(contentView, message, Snackbar.LENGTH_SHORT).show();
    }

    protected void startProxyFragment() {
        loadingNext = false;
        mainActivity.startFragment(proxyFragment);
    }

    protected void startFragment(Fragment fragment) {
        mainActivity.startFragment(fragment);
    }

    protected boolean isProxyFragmentNotDetached() {
        return !proxyFragment.isDetached();
    }

    protected boolean isLoadingNext() {
        return loadingNext;
    }

    public void setLoadingNext(boolean loadingNext) {
        this.loadingNext = loadingNext;
    }

    public void backPress() {
        mainActivity.onBackPressed();
    }
}
