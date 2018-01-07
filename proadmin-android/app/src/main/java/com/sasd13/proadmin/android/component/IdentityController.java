package com.sasd13.proadmin.android.component;

import android.support.v4.app.Fragment;

import com.sasd13.proadmin.android.activity.IdentityActivity;
import com.sasd13.proadmin.android.component.Controller;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public abstract class IdentityController extends Controller {

    protected IdentityController(IdentityActivity identityActivity) {
        super(identityActivity);
    }

    @Override
    protected IdentityActivity getActivity() {
        return (IdentityActivity) super.getActivity();
    }

    protected void startFragment(Fragment fragment) {
        getActivity().startFragment(fragment);
    }
}
