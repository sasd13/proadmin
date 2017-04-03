package com.sasd13.proadmin.controller;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.view.fragment.ProxyFragment;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public abstract class MainController extends Controller {

    protected MainActivity mainActivity;
    private ProxyFragment proxyFragment;
    private boolean loadingNext;

    protected MainController(Activity activity) {
        super(activity);

        mainActivity = (MainActivity) activity;
        proxyFragment = ProxyFragment.newInstance();
    }

    public abstract void entry();

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
