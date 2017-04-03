package com.sasd13.proadmin.controller;

import android.app.Activity;

import com.sasd13.proadmin.activity.IdentityActivity;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public abstract class IdentityController extends Controller {

    protected IdentityActivity identityActivity;

    protected IdentityController(Activity activity) {
        super(activity);

        identityActivity = (IdentityActivity) activity;
    }
}
