package com.sasd13.proadmin.controller;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.sasd13.proadmin.activity.MainActivity;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public abstract class MainController extends Controller {

    protected MainActivity mainActivity;

    protected MainController(Activity activity) {
        super(activity);

        mainActivity = (MainActivity) activity;
    }

    protected void startFragment(Fragment fragment) {
        mainActivity.startFragment(fragment);
    }
}
